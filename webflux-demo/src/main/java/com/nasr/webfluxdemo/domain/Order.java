package com.nasr.webfluxdemo.domain;

import com.nasr.webfluxdemo.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity<Long> {

    @Column(value = "orderDate")
    private LocalDateTime orderDate;

    @Column(value = "customerId")
    private Long customerId;

    @Column(value = "totalPrice")
    private BigDecimal totalPrice;

}
