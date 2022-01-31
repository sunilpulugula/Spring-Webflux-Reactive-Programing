package com.spring.boot.webflux.learning.router;

import com.spring.boot.webflux.learning.handler.OrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;

@Component
public class OrderRouter {

    @Autowired
    private OrderHandler orderHandler;

    @Bean
    public RouterFunction<ServerResponse>  orderRoutes(){
        return RouterFunctions.route(RequestPredicates.GET("/webflux/orders"), OrderHandler::getAllOrders);
    }
}
