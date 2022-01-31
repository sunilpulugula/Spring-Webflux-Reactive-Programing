package com.spring.boot.webflux.learning.handler;

import com.spring.boot.webflux.learning.model.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OrderHandler {

    public static Mono<ServerResponse> getAllOrders(ServerRequest request){
        Mono<ServerResponse> body = ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.just(
                                new Order(1, "abc"),
                                new Order(2, "bef"),
        new Order(1, "abc"),
                new Order(2, "bef"),
        new Order(1, "abc"),
                new Order(2, "bef"),
        new Order(1, "abc"),
                new Order(2, "bef")),

                        Order.class);
        return body;
    }
}
