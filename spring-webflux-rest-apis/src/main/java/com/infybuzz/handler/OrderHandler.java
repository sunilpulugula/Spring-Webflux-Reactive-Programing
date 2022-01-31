package com.infybuzz.handler;

import com.infybuzz.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.infybuzz.request.CreateOrderRequest;
import com.infybuzz.request.UpdateOrderRequest;
import com.infybuzz.response.OrderResponse;
import com.infybuzz.service.OrderService;

import reactor.core.publisher.Mono;

@Component
public class OrderHandler {
	
	@Autowired
	OrderService orderService;
	
	public Mono<ServerResponse> getAllOrders(ServerRequest serverRequest) {
		/*return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(orderService.getAllOrders(), Order.class);*/

		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(orderService.getAllOrders()
						.map(order -> orderService.mapOrderToOrderResponse(order))
				,OrderResponse.class);

		/*return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(orderService.getAllOrders()
						.map(order -> 
						orderService.mapOrderToOrderResponse(order)), 
						OrderResponse.class);*/
	}
	
	public Mono<ServerResponse> getOrderById(ServerRequest serverRequest) {
		//Long id = Long.valueOf(serverRequest.pathVariable("id"));
		
		Long id = Long.valueOf(serverRequest.queryParam("id").get());
		
		String firstHeader = serverRequest.headers().firstHeader("first-header");
		String secondHeader = serverRequest.headers().firstHeader("second-header");
		
		System.out.println(firstHeader);
		System.out.println(secondHeader);
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.header("response-header", "response-header-value")
				.body(orderService.getOrderById(id)
						.map(order ->
						orderService.mapOrderToOrderResponse(order)),
						OrderResponse.class);
	}
	
	public Mono<ServerResponse> createOrder(ServerRequest serverRequest) {
		Mono<CreateOrderRequest> monoCreateOrderRequest = 
				serverRequest.bodyToMono(CreateOrderRequest.class);

		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						monoCreateOrderRequest
						.map(orderService::mapCreateOrderRequestToOrder)
						.flatMap(order -> orderService.saveOrder(order))
						.map(order -> 
						orderService.mapOrderToOrderResponse(order)),
						OrderResponse.class
						);
	}
	
	public Mono<ServerResponse> updateOrder(ServerRequest serverRequest) {
		
		Mono<UpdateOrderRequest> monoUpdateOrderRequest = 
				serverRequest.bodyToMono(UpdateOrderRequest.class);
		
		Long id = Long.valueOf(serverRequest.pathVariable("id"));

		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						orderService.getOrderById(id)
						.flatMap(order -> 
						monoUpdateOrderRequest.map(request -> 
							orderService
							.mapUpdateOrderRequestToOrder(order, request)))
						.flatMap(order -> orderService.saveOrder(order))
						.map(order -> 
						orderService.mapOrderToOrderResponse(order)),
						OrderResponse.class
						);
		
	}
	
	public Mono<ServerResponse> deleteOrder(ServerRequest serverRequest) {
		Long id = Long.valueOf(serverRequest.pathVariable("id"));
		
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_PLAIN)
				.body(
						orderService.deleteOrder(id)
						.map(a -> "Order has been deleted"),
						String.class
						);
	}
}
