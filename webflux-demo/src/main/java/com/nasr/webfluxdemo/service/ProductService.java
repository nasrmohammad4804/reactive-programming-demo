package com.nasr.webfluxdemo.service;

import com.nasr.webfluxdemo.base.service.BaseService;
import com.nasr.webfluxdemo.domain.Product;
import com.nasr.webfluxdemo.dto.ProductDto;
import com.nasr.webfluxdemo.dto.ProductInfoDto;
import com.nasr.webfluxdemo.dto.view.OrderViewDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.stream.Stream;

public interface ProductService extends BaseService<Long, Product, ProductDto> {

    Mono<Long> count();

    Flux<ProductDto> getProductByCategoryId(Long categoryId);

    Flux<ProductInfoDto> getProductByIds(List<Long> productIds);

    Flux<Product> changeCapacities(Stream<OrderViewDto.ProductOrderViewDto> productOrderViewDtoStream);
}
