package com.nasr.webfluxdemo.repository;

import com.nasr.webfluxdemo.domain.OrderProduct;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderProductRepository extends ReactiveCrudRepository<OrderProduct,Long> {

    @Query("select op.id as oderProductId  , p.id as productId , p.name as productName " +
            ", op.productNumber as as productNumber from order_product as op join product as p on  p.id = op.productId where op.orderId = :id")
    Flux<Object[]> findOrderProductsByOrderId(Long id);
}
