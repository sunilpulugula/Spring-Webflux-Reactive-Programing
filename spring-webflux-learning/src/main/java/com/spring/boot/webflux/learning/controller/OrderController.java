package com.spring.boot.webflux.learning.controller;

import com.spring.boot.webflux.learning.model.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/api")
public class OrderController {


    @GetMapping(value= "/orders", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Order> getOrders(){
        Flux<Order> orderFlux = Flux.just(
                new Order(1,"def"),
                new Order(2,"abc"),
                new Order(3,"abc"),
                new Order(4,"abc"),
                new Order(5,"abc"),
                new Order(6,"abc"),
                new Order(7,"abc"),
                new Order(8,"abc"),
                new Order(9,"abc"),
                new Order(10,"abc"),
                new Order(11,"abc"),
                new Order(12,"abc"),
                new Order(13,"abc"),
                new Order(14,"abc"),
                new Order(15,"abc"),
                new Order(16,"abc")
        ).log().delayElements(Duration.ofSeconds(1));

        return orderFlux;
    }
}
