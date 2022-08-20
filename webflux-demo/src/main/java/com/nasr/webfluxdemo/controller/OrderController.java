package com.nasr.webfluxdemo.controller;


import com.nasr.webfluxdemo.dto.OrderDto;
import com.nasr.webfluxdemo.dto.response.ResponseDto;
import com.nasr.webfluxdemo.dto.view.OrderViewDto;
import com.nasr.webfluxdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{customerId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public Flux<OrderDto> getCustomerOrders(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @PostMapping("/{customerId}")
    public Mono<ResponseEntity<ResponseDto<OrderDto>>> makeOrder(@RequestBody @Valid OrderViewDto orderViewDto
            , @PathVariable Long customerId) {

        return orderService.saveOrUpdate(orderViewDto, customerId)
                .map(orderDto -> ResponseEntity.ok(
                        new ResponseDto<>(true, orderDto, "ok")
                ))
                .log();

    }

}
