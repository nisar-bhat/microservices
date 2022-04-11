package com.nisar.api.core.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import reactor.core.publisher.Mono;

public interface ProductService {
	
	/*
	@GetMapping(value ="/product/{productId}" , produces ="application/json")
	Product getProduct(@PathVariable  int productId); 
	
	@PostMapping(value ="/product" ,consumes = "application/json", produces ="application/json")
	Product createProduct(@RequestBody Product product  ); 
	
	@DeleteMapping(value ="/product/{productId}")
	Product deleteProduct(@PathVariable  int productId); */
	
	@PostMapping(value ="/product" ,consumes = "application/json", produces ="application/json")
	 Mono<Product> createProduct(@RequestBody Product body);

	  /**
	   * Sample usage: "curl $HOST:$PORT/product/1".
	   *
	   * @param productId Id of the product
	   * @return the product, if found, else null
	   */
	  @GetMapping( value = "/product/{productId}", produces = "application/json")
	  Mono<Product> getProduct(@PathVariable int productId);

	  Mono<Void> deleteProduct(int productId);
}
