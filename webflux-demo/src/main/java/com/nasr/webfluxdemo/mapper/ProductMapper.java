package com.nasr.webfluxdemo.mapper;

import com.nasr.webfluxdemo.base.mapper.BaseMapper;
import com.nasr.webfluxdemo.domain.Product;
import com.nasr.webfluxdemo.dto.ProductDto;
import com.nasr.webfluxdemo.dto.view.ProductViewDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper  extends BaseMapper<Product, ProductDto, ProductViewDto> {
}
