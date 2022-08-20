package com.nasr.webfluxdemo.service;

import com.nasr.webfluxdemo.base.service.BaseService;
import com.nasr.webfluxdemo.domain.Order;
import com.nasr.webfluxdemo.dto.OrderDto;
import com.nasr.webfluxdemo.dto.view.OrderViewDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService extends BaseService<Long, Order, OrderDto> {

    Flux<OrderDto> getOrdersByCustomerId(Long customerId);

    Mono<OrderDto> saveOrUpdate(OrderViewDto dto , Long customerId);
}
