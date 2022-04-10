package com.nisar.api.core.review;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewService {
/*
	  @GetMapping( value = "/review/{productId}",   produces = "application/json")
	 List<Review> getReviews(@PathVariable(value = "productId", required = true) int productId);
	  
	 @PostMapping(value ="/review" ,consumes = "application/json", produces ="application/json")
	 Review createReview(@RequestBody Review review  ); 
	 
	 @DeleteMapping(value ="/product/{productId}")
	Review deleteReview(@PathVariable  int productId); 
	*/
	
	Mono<Review> createReview(Review body);

	  /**
	   * Sample usage: "curl $HOST:$PORT/review?productId=1".
	   *
	   * @param productId Id of the product
	   * @return the reviews of the product
	   */
	  @GetMapping( value = "/review", produces = "application/json")
	  Flux<Review> getReviews(@RequestParam(value = "productId", required = true) int productId);

	  Mono<Void> deleteReviews(int productId);
	}
	

