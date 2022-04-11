package com.nisar.core.product;


import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

public abstract class MongoDbTestBase {
  private static MongoDBContainer database = new MongoDBContainer("mongo:4.4.2");
  
  static {
    database.start();
  } 
  
  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
	  String host= "mongodb://"+ database.getContainerIpAddress()+":"+database.getMappedPort(27017)+"/test";
	  
	  registry.add("spring.data.mongodb.uri",()->host );
      registry.add("spring.data.mongodb.database", () -> "test");
    
  }
}
