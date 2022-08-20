package com.nasr.webfluxdemo.service.impl;

import com.nasr.webfluxdemo.base.mapper.BaseMapper;
import com.nasr.webfluxdemo.base.service.impl.BaseServiceImpl;
import com.nasr.webfluxdemo.domain.Customer;
import com.nasr.webfluxdemo.dto.CustomerDto;
import com.nasr.webfluxdemo.dto.view.CustomerViewDto;
import com.nasr.webfluxdemo.mapper.CustomerMapper;
import com.nasr.webfluxdemo.repository.CustomerRepository;
import com.nasr.webfluxdemo.service.CustomerService;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, ReactiveCrudRepository<Customer, Long>, CustomerViewDto, CustomerDto> implements CustomerService {

    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper customerMapper) {
        super(repository);
        this.customerMapper = customerMapper;
    }


    @Override
    public BaseMapper<Customer, CustomerDto, CustomerViewDto> getMapper() {
        return customerMapper;
    }
}
