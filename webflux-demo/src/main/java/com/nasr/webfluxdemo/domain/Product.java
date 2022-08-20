package com.nasr.webfluxdemo.domain;

import com.nasr.webfluxdemo.base.domain.BaseEntity;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity<Long> {
    private String name;

    @Column(value = "categoryId")
    private Long categoryId;

    private Long capacity;

    private Double price;

    public Product(Long id, String name, Long capacity, Double price) {
        super(id);
        this.name = name;
        this.capacity = capacity;
        this.price = price;
    }

    public Product(String name, Long capacity, Double price) {
        this(name,null,capacity,price);
    }
}
