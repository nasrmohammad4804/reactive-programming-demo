package com.nasr.webfluxdemo.repository;

import com.nasr.webfluxdemo.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer,Long> {

}
