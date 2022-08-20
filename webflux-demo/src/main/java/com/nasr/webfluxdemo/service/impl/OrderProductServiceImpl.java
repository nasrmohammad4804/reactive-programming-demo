package com.nasr.webfluxdemo.service.impl;

import com.nasr.webfluxdemo.base.mapper.BaseMapper;
import com.nasr.webfluxdemo.base.service.impl.BaseServiceImpl;
import com.nasr.webfluxdemo.converter.ConvertObjectToOrderProductDto;
import com.nasr.webfluxdemo.domain.OrderProduct;
import com.nasr.webfluxdemo.dto.OrderProductsDto;
import com.nasr.webfluxdemo.dto.view.OrderProductViewDto;
import com.nasr.webfluxdemo.mapper.OrderProductMapper;
import com.nasr.webfluxdemo.repository.OrderProductRepository;
import com.nasr.webfluxdemo.service.OrderProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OrderProductServiceImpl extends
        BaseServiceImpl<OrderProduct, Long, OrderProductRepository, OrderProductViewDto, OrderProductsDto>
        implements OrderProductService {

    private final OrderProductMapper orderProductMapper;

    public OrderProductServiceImpl(OrderProductRepository repository,OrderProductMapper orderProductMapper) {
        super(repository);
        this.orderProductMapper  = orderProductMapper;
    }

    @Override
    public BaseMapper<OrderProduct, OrderProductsDto, OrderProductViewDto> getMapper() {
        return orderProductMapper;
    }

    @Override
    public Flux<OrderProductsDto> getOrderProductsByOrderId(Long id) {
        return repository.findOrderProductsByOrderId(id)
                .map(ConvertObjectToOrderProductDto::convert);
    }
}
