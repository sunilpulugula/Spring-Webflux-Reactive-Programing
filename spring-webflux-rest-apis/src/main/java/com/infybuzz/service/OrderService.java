package com.infybuzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infybuzz.entity.Order;
import com.infybuzz.repository.OrderRepository;
import com.infybuzz.request.CreateOrderRequest;
import com.infybuzz.request.UpdateOrderRequest;
import com.infybuzz.response.OrderResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public Flux<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	public OrderResponse mapOrderToOrderResponse(Order order) {
		return new OrderResponse(order.getId(), 
				order.getAmount(), order.getPlacedDate());
	}
	
	public Mono<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}
	
	public Order mapCreateOrderRequestToOrder (CreateOrderRequest createOrderRequest) {
		return new Order(createOrderRequest.getAmount(), 
				createOrderRequest.getPlacedDate());
	}
	
	public Mono<Order> saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public Order mapUpdateOrderRequestToOrder(Order order, 
			UpdateOrderRequest updateOrderRequest) {
		order.setAmount(updateOrderRequest.getAmount());
		order.setPlacedDate(updateOrderRequest.getPlacedDate());
		
		return order;
	}
	
	public Mono<Void> deleteOrder(Long id) {
		return orderRepository.deleteById(id);
	}
}
