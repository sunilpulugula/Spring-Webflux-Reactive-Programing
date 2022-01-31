package com.infybuzz.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.infybuzz.entity.Order;

@Repository
public interface OrderRepository extends R2dbcRepository<Order, Long> {

}
