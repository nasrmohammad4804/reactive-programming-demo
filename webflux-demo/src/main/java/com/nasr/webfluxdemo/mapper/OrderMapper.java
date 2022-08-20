package com.nasr.webfluxdemo.mapper;

import com.nasr.webfluxdemo.base.mapper.BaseMapper;
import com.nasr.webfluxdemo.domain.Order;
import com.nasr.webfluxdemo.dto.OrderDto;
import com.nasr.webfluxdemo.dto.view.OrderViewDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderDto, OrderViewDto> {
}
