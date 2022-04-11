package com.nisar.core.review.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nisar.core.review.domain.ReviewEntity;

public interface ReviewRepository extends CrudRepository<ReviewEntity, Integer> {
	@Transactional(readOnly = true)
	List<ReviewEntity>findByProductId(int productId);
}
