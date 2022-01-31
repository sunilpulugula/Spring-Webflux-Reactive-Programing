package com.spring.boot.webflux.learning.springwebfluxlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.spring.boot.webflux.learning","com.spring.boot.webflux.learning.handler","com.spring.boot.webflux.learning.router"})
public class SpringWebfluxLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxLearningApplication.class, args);
	}

}
