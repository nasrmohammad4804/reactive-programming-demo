package com.nasr.webfluxdemo.repository;

import com.nasr.webfluxdemo.domain.Order;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order,Long> {

    @Query("select o.* from orders as o where o.customerId = :customerId")
    Flux<Order> findAllByCustomerId(Long customerId);
}
