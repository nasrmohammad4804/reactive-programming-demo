package com.nasr.webfluxdemo.service.impl;

import com.nasr.webfluxdemo.base.mapper.BaseMapper;
import com.nasr.webfluxdemo.base.service.impl.BaseServiceImpl;
import com.nasr.webfluxdemo.domain.Order;
import com.nasr.webfluxdemo.domain.OrderProduct;
import com.nasr.webfluxdemo.dto.OrderDto;
import com.nasr.webfluxdemo.dto.OrderProductsDto;
import com.nasr.webfluxdemo.dto.ProductInfoDto;
import com.nasr.webfluxdemo.dto.view.OrderViewDto;
import com.nasr.webfluxdemo.exception.IllegalProductException;
import com.nasr.webfluxdemo.mapper.OrderMapper;
import com.nasr.webfluxdemo.repository.OrderRepository;
import com.nasr.webfluxdemo.service.OrderProductService;
import com.nasr.webfluxdemo.service.OrderService;
import com.nasr.webfluxdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long, OrderRepository, OrderViewDto, OrderDto>
        implements OrderService {

    private final OrderMapper orderMapper;

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private ProductService productService;


    public OrderServiceImpl(OrderRepository repository, OrderMapper orderMapper) {
        super(repository);
        this.orderMapper = orderMapper;
    }

    @Override
    public BaseMapper<Order, OrderDto, OrderViewDto> getMapper() {
        return orderMapper;
    }

    @Override
    public Flux<OrderDto> getOrdersByCustomerId(Long customerId) {
        return repository.findAllByCustomerId(customerId)
                .flatMap(data -> {
                    Mono<List<OrderProductsDto>> orderProducts = orderProductService.getOrderProductsByOrderId(data.getId())
                            .collectList();

                    return Mono.zip(Mono.just(data), orderProducts, ((order, orderProductsDtos) ->
                            new OrderDto(data.getId(), orderProductsDtos, order.getOrderDate(), order.getTotalPrice())));

                })
                .log();
    }

    @Override
    @Transactional
    public Mono<OrderDto> saveOrUpdate(OrderViewDto dto, Long customerId) {
        List<Long> productIds = getProductIds(dto);
        Flux<ProductInfoDto> products = productService.getProductByIds(productIds);

        Mono<OrderDto> order = calculateTotalPrice(dto.getProductOrderViewDtos(), products)
                .flatMap(totalPrice -> super.saveOrUpdate(new Order(LocalDateTime.now(), customerId, totalPrice)));

        return order.flatMap(o -> products.zipWithIterable(dto.getProductOrderViewDtos())
                .map(tuple2 -> {
                    if (tuple2.getT2().getProductNumber() > tuple2.getT1().capacity())
                        throw new IllegalProductException("your product number greater store capacity");

                    return tuple2;


                })
                .collectList()
                .flatMap(tuple2s -> {
                    List<OrderProduct> orderProducts = tuple2s.stream().map(Tuple2::getT2)
                            .map(productOrderViewDto ->
                                    new OrderProduct(o.getId(), productOrderViewDto.getProductId(), productOrderViewDto.getProductNumber()))
                            .collect(Collectors.toList());

                    productService.changeCapacities(tuple2s.stream().map(Tuple2::getT2))
                            .subscribeOn(Schedulers.parallel())
                            .subscribe(product -> {
                            });

                    return orderProductService.saveAll(orderProducts)
                            .collectList()
                            .zipWith(Mono.just(o), (orderProductsDtos, orderDto) ->
                                    new OrderDto(o.getId(), orderProductsDtos, o.getOrderDate(), o.getTotalPrice()));

                }));

    }

    private Mono<BigDecimal> calculateTotalPrice(List<OrderViewDto.ProductOrderViewDto> productOrderViewDtos, Flux<ProductInfoDto> products) {

        return products.zipWithIterable(productOrderViewDtos)
                .map(tuple2 -> tuple2.getT1().price() * tuple2.getT2().getProductNumber())
                .collectList()
                .map(prices -> {
                    BigDecimal result = new BigDecimal(0);
                    double sum = prices.stream().mapToDouble((price) -> price)
                            .sum();

                    return new BigDecimal(sum);
                })
                .log();


    }

    private List<Long> getProductIds(OrderViewDto dto) {
        return dto.getProductOrderViewDtos()
                .stream().map(OrderViewDto.ProductOrderViewDto::getProductId)
                .collect(Collectors.toList());
    }


}
