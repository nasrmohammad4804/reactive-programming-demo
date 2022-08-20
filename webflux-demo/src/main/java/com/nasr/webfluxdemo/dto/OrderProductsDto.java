package com.nasr.webfluxdemo.dto;

import lombok.Builder;

@Builder
public record OrderProductsDto(Long id,ProductDto product, Long productNumber) { }
