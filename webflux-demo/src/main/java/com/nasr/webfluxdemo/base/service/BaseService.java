package com.nasr.webfluxdemo.base.service;

import com.nasr.webfluxdemo.base.domain.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.sql.rowset.serial.SerialStruct;
import java.io.Serializable;


public interface BaseService<ID extends Serializable, E extends BaseEntity<ID>,D> {

    Mono<D> saveOrUpdate(E entity);

    Mono<D> getById(ID id);

    Flux<D> getAll();

    Mono<Void> deleteById(ID id);

    Flux<D> saveAll(Iterable<E> entities);
}
