package com.nasr.webfluxdemo.base.service.impl;

import com.nasr.webfluxdemo.base.domain.BaseEntity;
import com.nasr.webfluxdemo.base.mapper.BaseMapper;
import com.nasr.webfluxdemo.base.service.BaseService;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;



public abstract class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Serializable, R extends ReactiveCrudRepository<E, ID>, V, D>
        implements BaseService<ID, E, D> {

    protected final R repository;

    public BaseServiceImpl(  R repository) {
        this.repository = repository;
    }

    public abstract BaseMapper<E,D,V> getMapper();

    @Override
    public Mono<D> saveOrUpdate(E entity) {
        return repository.save(entity)
                .map(data -> getMapper().convertEntityToDto(data))
                .log();
    }

    @Override
    public Mono<D> getById(ID id) {
        return repository.findById(id)
                .map(data -> getMapper().convertEntityToDto(data))
                .log();
    }

    @Override
    public Flux<D> getAll() {
        return repository.findAll()
                .map(entity -> getMapper().convertEntityToDto(entity))
                .log();
    }

    @Override
    public Mono<Void> deleteById(ID id) {

        return repository.deleteById(id)
                .log();
    }

    @Override
    @Transactional
    public Flux<D> saveAll(Iterable<E> entities) {
        return repository.saveAll(entities)
                .map(entity -> getMapper().convertEntityToDto(entity))
                .log();
    }
}
