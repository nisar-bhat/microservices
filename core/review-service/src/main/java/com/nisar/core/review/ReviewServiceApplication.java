package com.nisar.core.review;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@ComponentScan("com.nisar")
public class ReviewServiceApplication {
	static Logger LOG=LoggerFactory.getLogger(ReviewServiceApplication.class);
  public static void main(String[] args) {
   ConfigurableApplicationContext context= SpringApplication.run(ReviewServiceApplication.class, args);
  
   
  }
  
  @Bean
  public Scheduler jdbcScheduler() {

  // As is suggested in https://wiki.postgresql.org/wiki/Number_Of_Database_Connections,
  // the number of active connections = ((core_count * 2) + effective_spindle_count) for PostgreSQL    
  int connectionPoolSize = 4 * 2 + 1;

  return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
  }
}