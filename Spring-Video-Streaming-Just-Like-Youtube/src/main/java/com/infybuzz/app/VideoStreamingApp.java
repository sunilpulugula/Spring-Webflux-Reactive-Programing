package com.infybuzz.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.infybuzz.router", 
	"com.infybuzz.handler", "com.infybuzz.service"})
public class VideoStreamingApp {

	public static void main(String[] args) {
		SpringApplication.run(VideoStreamingApp.class, args);
	}

}
