package com.nasr.webfluxdemo.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderViewDto {

    private List<ProductOrderViewDto> productOrderViewDtos = new ArrayList<>();

    @Data
    public static final class ProductOrderViewDto{
        private Long productId;
        private Long productNumber;
    }
}
