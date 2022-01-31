package com.infybuzz.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.infybuzz.service.VideoService;

import reactor.core.publisher.Mono;

@Component
public class VideoHandler {
	
	@Autowired
	VideoService videoService;
	
	public Mono<ServerResponse> watchVideo(ServerRequest serverRequest) {
		
		String rangeHeader = serverRequest.headers().firstHeader("Range");
		System.out.println(rangeHeader);
		String videoFile = serverRequest.queryParam("v").get();
		return ServerResponse.ok()
				.body(videoService.getVideo(videoFile), Resource.class);
	}
}
