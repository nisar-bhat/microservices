package com.nisar.core.product.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.nisar.core.product.domain.ProductEntity;

import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, String> {
	  Mono<ProductEntity> findByProductId(int productId);
	}