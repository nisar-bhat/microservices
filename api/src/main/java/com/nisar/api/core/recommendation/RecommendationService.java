package com.nisar.api.core.recommendation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecommendationService {
/*
	  @GetMapping(value = "/recommendation/{productId}", produces = "application/json")
	  List<Recommendation> getRecommendations(@PathVariable(value = "productId", required = true) int productId);
	  @PostMapping(value ="/recommendation" ,consumes = "application/json", produces ="application/json")
	  Recommendation createRecommendations(@RequestBody Recommendation recommendation);
	  @DeleteMapping(value = "/recommendation/{productId}", produces = "application/json")
	  Recommendation deleteRecommendations(@PathVariable(value = "productId", required = true) int productId);*/
	
	Mono<Recommendation> createRecommendation(Recommendation body);

	  /**
	   * Sample usage: "curl $HOST:$PORT/recommendation?productId=1".
	   *
	   * @param productId Id of the product
	   * @return the recommendations of the product
	   */
	  @GetMapping(value = "/recommendation",  produces = "application/json")
	  Flux<Recommendation> getRecommendations(@RequestParam(value = "productId", required = true) int productId);

	  Mono<Void> deleteRecommendations(int productId);
}
