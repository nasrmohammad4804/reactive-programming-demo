package com.nasr.webfluxdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    //as oneToMany relation
    private Long id;
    private List<OrderProductsDto> productDtoList;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
}
