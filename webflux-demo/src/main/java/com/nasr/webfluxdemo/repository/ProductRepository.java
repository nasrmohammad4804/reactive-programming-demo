package com.nasr.webfluxdemo.repository;

import com.nasr.webfluxdemo.domain.Product;
import com.nasr.webfluxdemo.dto.ProductDto;
import com.nasr.webfluxdemo.dto.ProductInfoDto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ProductRepository extends ReactiveCrudRepository<Product,Long> {

    Flux<Product> findByCategoryId(Long categoryId);

    @Query("select p.id , p.capacity, p.price from product as p where p.id in (:ids)")
    Flux<ProductInfoDto> findAllByIds(List<Long > ids);
}
