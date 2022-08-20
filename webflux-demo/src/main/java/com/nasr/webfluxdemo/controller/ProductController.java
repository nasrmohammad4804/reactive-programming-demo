package com.nasr.webfluxdemo.controller;

import com.nasr.webfluxdemo.domain.Product;
import com.nasr.webfluxdemo.dto.OrderDto;
import com.nasr.webfluxdemo.dto.ProductDto;
import com.nasr.webfluxdemo.dto.response.ResponseDto;
import com.nasr.webfluxdemo.dto.view.OrderViewDto;
import com.nasr.webfluxdemo.dto.view.ProductViewDto;
import com.nasr.webfluxdemo.mapper.ProductMapper;
import com.nasr.webfluxdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @PostMapping
    public Mono<ResponseEntity<ResponseDto<ProductDto>>> saveProduct(@RequestBody @Valid ProductViewDto product) {
        Product entity = productMapper.convertViewDtoToEntity(product);
        return productService.saveOrUpdate(entity)
                .map(data -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseDto<>(true, data, "ok")));
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public Flux<ResponseDto<ProductDto>> getProducts() {
        return productService.getAll()
                .map(data -> new ResponseDto<>(true, data, "ok"));
    }

    @GetMapping("/category/{categoryId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Flux<ProductDto> getProductByCategory(@PathVariable Long categoryId) {
        return productService.getProductByCategoryId(categoryId);
    }

}
