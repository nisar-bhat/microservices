package com.nisar.core.review.controller;

import static java.util.logging.Level.FINE;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import com.nisar.api.core.exception.InvalidInputException;
import com.nisar.api.core.review.Review;
import com.nisar.api.core.review.ReviewService;
import com.nisar.core.review.domain.ReviewEntity;
import com.nisar.core.review.repository.ReviewRepository;
import com.nisar.util.ServiceUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
 

@RestController
public class ReviewServiceController implements ReviewService,ReviewMapper {
  private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceController.class);

  private final ReviewRepository repository;

  private final ServiceUtil serviceUtil;

    @Autowired
	@Qualifier("jdbcScheduler")
	private Scheduler jdbcScheduler;
 

  @Autowired
  public ReviewServiceController(ReviewRepository repository, ServiceUtil serviceUtil) {
    this.repository = repository;
     
    this.serviceUtil = serviceUtil;
  }

  @Override
  public Mono<Review> createReview(Review body) {

    if (body.getProductId() < 1) {
      throw new InvalidInputException("Invalid productId: " + body.getProductId());
    }
    return Mono.fromCallable(() -> internalCreateReview(body))
      .subscribeOn(jdbcScheduler);
  }

  private Review internalCreateReview(Review body) {
    try {
      ReviewEntity entity = apiToEntity(body);
      ReviewEntity newEntity = repository.save(entity);

      LOG.debug("createReview: created a review entity: {}/{}", body.getProductId(), body.getReviewId());
      return  entityToApi(newEntity);

    } catch (DataIntegrityViolationException dive) {
      throw new InvalidInputException("Duplicate key, Product Id: " + body.getProductId() + ", Review Id:" + body.getReviewId());
    }
  }

  @Override
  public Flux<Review> getReviews(int productId) {

    if (productId < 1) {
      throw new InvalidInputException("Invalid productId: " + productId);
    }

    LOG.info("Will get reviews for product with id={}", productId);

    return Mono.fromCallable(() -> internalGetReviews(productId))
      .flatMapMany(Flux::fromIterable)
      .log(LOG.getName(), FINE)
      .subscribeOn(jdbcScheduler);
  }

  private List<Review> internalGetReviews(int productId) {

    List<ReviewEntity> entityList = repository.findByProductId(productId);
    List<Review> list = entityListToApiList(entityList);
    list.forEach(e -> e.setServiceAddress(serviceUtil.getServiceAddress()));

    LOG.debug("Response size: {}", list.size());

    return list;
  }

  @Override
  public Mono<Void> deleteReviews(int productId) {

    if (productId < 1) {
      throw new InvalidInputException("Invalid productId: " + productId);
    }

    return Mono.fromRunnable(() -> internalDeleteReviews(productId)).subscribeOn(jdbcScheduler).then();
  }

  private void internalDeleteReviews(int productId) {

    LOG.debug("deleteReviews: tries to delete reviews for the product with productId: {}", productId);

    repository.deleteAll(repository.findByProductId(productId));
  }
}