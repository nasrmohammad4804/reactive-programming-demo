package com.nasr.webfluxdemo.domain;

import com.nasr.webfluxdemo.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_category")
public class ProductCategory extends BaseEntity<Long> {
    private String name;
}
