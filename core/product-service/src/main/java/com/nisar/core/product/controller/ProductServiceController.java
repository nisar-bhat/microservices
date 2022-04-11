package com.nisar.core.product.controller;

import static java.util.logging.Level.FINE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DuplicateKeyException;
import com.nisar.api.core.exception.InvalidInputException;
import com.nisar.api.core.exception.NotFoundException;
import com.nisar.api.core.product.Product;
import com.nisar.api.core.product.ProductService;
import com.nisar.core.product.domain.ProductEntity;
import com.nisar.core.product.repository.ProductRepository;

import com.nisar.util.ServiceUtil;

import reactor.core.publisher.Mono;

@RestController
public class ProductServiceController implements ProductService,ProductMapper {
	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceController.class);

	  private final ServiceUtil serviceUtil;

	  private final ProductRepository repository;

	   
	  //private  ProductMapper mapper;

	  @Autowired
	  public ProductServiceController(ProductRepository repository,ServiceUtil serviceUtil) {
	    this.repository = repository;
	 
	  this.serviceUtil = serviceUtil;
	  }

	  @Override
	  public Mono<Product> createProduct(Product body) {

	    if (body.getProductId() < 1) {
	      throw new InvalidInputException("Invalid productId: " + body.getProductId());
	    }
	    
	    if (body.getProductId() >= 13) {
		      throw new InvalidInputException("Invalid productId: " + body.getProductId());
		    }

	    ProductEntity entity = apiToEntity(body);
	    Mono<Product> newEntity = repository.save(entity)
	      .log(LOG.getName(), FINE)
	      .onErrorMap(
	        DuplicateKeyException.class,
	        ex -> new InvalidInputException("Duplicate key, Product Id: " + body.getProductId()))
	      .map(e -> entityToApi(e));

	    return newEntity;
	  }

	  @Override
	  public Mono<Product> getProduct(int productId) {

	    if (productId < 1) {
	      throw new InvalidInputException("Invalid productId: " + productId);
	    }

	    LOG.info("Will get product info for id={}", productId);

	    return repository.findByProductId(productId)
	      .switchIfEmpty(Mono.error(new NotFoundException("No product found for productId: " + productId)))
	      .log(LOG.getName(), FINE)
	      .map(e -> entityToApi(e))
	      .map(e -> setServiceAddress(e));
	  }

	  @Override
	  public Mono<Void> deleteProduct(int productId) {

	    if (productId < 1) {
	      throw new InvalidInputException("Invalid productId: " + productId);
	    }

	    LOG.debug("deleteProduct: tries to delete an entity with productId: {}", productId);
	    return repository.findByProductId(productId).log(LOG.getName(), FINE).map(e -> repository.delete(e)).flatMap(e -> e);
	  }

	  private Product setServiceAddress(Product e) {
	  e.setServiceAddress(serviceUtil.getServiceAddress());
	    return e;
	  }
	  
	}
