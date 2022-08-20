package com.nasr.webfluxdemo.converter;

import com.nasr.webfluxdemo.dto.OrderProductsDto;
import com.nasr.webfluxdemo.dto.ProductDto;

public class ConvertObjectToOrderProductDto {

    public static OrderProductsDto convert(Object[] objects) {
        Long orderProductId = (Long) objects[0];
        Long productId = (Long) objects[1];
        String productName = (String) objects[2];
        Long productNumber = (Long) objects[3];

        return new OrderProductsDto(orderProductId, new ProductDto(productId, productName), productNumber);
    }
}
