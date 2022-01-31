package com.infybuzz.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@ComponentScan({"com.infybuzz.router", 
	"com.infybuzz.handler", "com.infybuzz.service"})
@EntityScan("com.infybuzz.entity")
@EnableR2dbcRepositories("com.infybuzz.repository")
public class SpringWebfluxCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxCrudApplication.class, args);
	}

}
