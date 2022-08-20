package com.nasr.webfluxdemo.service.impl;

import com.nasr.webfluxdemo.base.mapper.BaseMapper;
import com.nasr.webfluxdemo.base.service.impl.BaseServiceImpl;
import com.nasr.webfluxdemo.domain.Product;
import com.nasr.webfluxdemo.dto.ProductDto;
import com.nasr.webfluxdemo.dto.ProductInfoDto;
import com.nasr.webfluxdemo.dto.view.OrderViewDto;
import com.nasr.webfluxdemo.dto.view.ProductViewDto;
import com.nasr.webfluxdemo.exception.ProductNotFoundException;
import com.nasr.webfluxdemo.mapper.ProductMapper;
import com.nasr.webfluxdemo.repository.ProductRepository;
import com.nasr.webfluxdemo.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long, ProductRepository, ProductViewDto, ProductDto>
        implements ProductService {

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository repository, ProductMapper productMapper) {
        super(repository);
        this.productMapper = productMapper;
    }

    @Override
    public BaseMapper<Product, ProductDto, ProductViewDto> getMapper() {
        return productMapper;
    }

    @Override
    public Mono<Long> count() {
        return repository.count();
    }

    @Override
    public Flux<ProductDto> getProductByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId)
                .map(productMapper::convertEntityToDto)
                .log()
                .switchIfEmpty(
                        Mono.error(() ->
                                new ProductNotFoundException("dont find any product with category id : " + categoryId))
                );
    }

    @Override
    public Flux<ProductInfoDto> getProductByIds(List<Long> productIds) {
        return repository.findAllByIds(productIds);
    }

    @Override
    @Transactional
    public Flux<Product> changeCapacities(Stream<OrderViewDto.ProductOrderViewDto> productOrderViewDtoStream) {

        List<OrderViewDto.ProductOrderViewDto> products = productOrderViewDtoStream.toList();

        List<Long> productIds = products.stream()
                .map(OrderViewDto.ProductOrderViewDto::getProductId).toList();

        return repository.findAllById(productIds)
                .zipWithIterable(products)
                .flatMap(tuple2 -> {
                    Product product = tuple2.getT1();
                    product.setCapacity(product.getCapacity() - tuple2.getT2().getProductNumber());
                    return repository.save(product);
                })
                .log();

    }
}
