package com.nasr.webfluxdemo.service;

import com.nasr.webfluxdemo.base.service.BaseService;
import com.nasr.webfluxdemo.domain.OrderProduct;
import com.nasr.webfluxdemo.dto.OrderProductsDto;
import reactor.core.publisher.Flux;

public interface OrderProductService extends BaseService<Long, OrderProduct, OrderProductsDto> {
    Flux<OrderProductsDto> getOrderProductsByOrderId(Long id);
}
