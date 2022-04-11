package com.nisar.core.review.controller;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.nisar.api.core.review.Review;
import com.nisar.core.review.domain.ReviewEntity;
 

 
public interface ReviewMapper {

  @Mappings({
    @Mapping(target = "serviceAddress", ignore = true)
  })
 default Review entityToApi(ReviewEntity entity) {
	  if (entity == null)
	      return null; 
	    Review review = new Review();
	    review.setProductId(entity.getProductId());
	    review.setReviewId(entity.getReviewId());
	    review.setAuthor(entity.getAuthor());
	    review.setSubject(entity.getSubject());
	    review.setContent(entity.getContent());
	    return review;
  }

  @Mappings({
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "version", ignore = true)
  })
  default ReviewEntity apiToEntity(Review api) {
	  if (api == null)
	      return null; 
	    ReviewEntity reviewEntity = new ReviewEntity();
	    reviewEntity.setProductId(api.getProductId());
	    reviewEntity.setReviewId(api.getReviewId());
	    reviewEntity.setAuthor(api.getAuthor());
	    reviewEntity.setSubject(api.getSubject());
	    reviewEntity.setContent(api.getContent());
	    return reviewEntity;
  }

  default List<Review> entityListToApiList(List<ReviewEntity> entity){
	  if (entity == null)
	      return null; 
	    List<Review> list = new ArrayList<>(entity.size());
	    for (ReviewEntity reviewEntity : entity)
	      list.add(entityToApi(reviewEntity)); 
	    return list;
  }

  default List<ReviewEntity> apiListToEntityList(List<Review> api){
	  if (api == null)
	      return null; 
	    List<ReviewEntity> list = new ArrayList<>(api.size());
	    for (Review review : api)
	      list.add(apiToEntity(review)); 
	    return list;
  }
}