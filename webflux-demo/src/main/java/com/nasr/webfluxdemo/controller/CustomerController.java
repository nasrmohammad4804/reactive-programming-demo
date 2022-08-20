package com.nasr.webfluxdemo.controller;

import com.nasr.webfluxdemo.domain.Customer;
import com.nasr.webfluxdemo.dto.CustomerDto;
import com.nasr.webfluxdemo.dto.response.ResponseDto;
import com.nasr.webfluxdemo.dto.view.CustomerViewDto;
import com.nasr.webfluxdemo.mapper.CustomerMapper;
import com.nasr.webfluxdemo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper mapper;

    @PostMapping
    @Operation(summary = "save customer ",responses = {
            @ApiResponse(responseCode = "200" ,description = "after saving customer")
    })
    public Mono<ResponseEntity<ResponseDto<CustomerDto>>> saveCustomer(@RequestBody @Valid CustomerViewDto customerViewDto) {

        Customer customer = mapper.convertViewDtoToEntity(customerViewDto);
        return customerService.saveOrUpdate(customer)
                .map(data -> ResponseEntity.ok(new ResponseDto<>(true, data, "ok")));

    }
}
