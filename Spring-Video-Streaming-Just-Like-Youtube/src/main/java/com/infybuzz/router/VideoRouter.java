package com.infybuzz.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.infybuzz.handler.VideoHandler;

@Configuration
public class VideoRouter {
	
	@Autowired
	VideoHandler videoHandler;
	
	@Bean
	public RouterFunction<ServerResponse> videoRoutes() {
		return RouterFunctions.route(RequestPredicates.GET("/video"), 
				videoHandler::watchVideo);
	}
}
