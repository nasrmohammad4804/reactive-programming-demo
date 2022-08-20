package com.nasr.webfluxdemo.domain;

import com.nasr.webfluxdemo.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_product")
public class OrderProduct extends BaseEntity<Long> {

    @Column(value = "orderId")
    private Long orderId;

    @Column(value = "productId")
    private Long productId;

    @Column(value = "productNumber")
    private Long productNumber;

}
