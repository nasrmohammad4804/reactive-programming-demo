package com.nasr.webfluxdemo.mapper;

import com.nasr.webfluxdemo.base.mapper.BaseMapper;
import com.nasr.webfluxdemo.domain.OrderProduct;
import com.nasr.webfluxdemo.dto.OrderProductsDto;
import com.nasr.webfluxdemo.dto.view.OrderProductViewDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderProductMapper extends BaseMapper<OrderProduct, OrderProductsDto, OrderProductViewDto> {
}
