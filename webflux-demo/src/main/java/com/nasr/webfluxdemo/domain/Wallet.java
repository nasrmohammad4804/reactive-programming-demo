package com.nasr.webfluxdemo.domain;

import com.nasr.webfluxdemo.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends BaseEntity<Long> {

    private Double amount;
    private Long customerId;
}
