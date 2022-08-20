package com.nasr.webfluxdemo.mapper;

import com.nasr.webfluxdemo.base.mapper.BaseMapper;
import com.nasr.webfluxdemo.domain.Customer;
import com.nasr.webfluxdemo.dto.CustomerDto;
import com.nasr.webfluxdemo.dto.view.CustomerViewDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer, CustomerDto, CustomerViewDto> {

}
