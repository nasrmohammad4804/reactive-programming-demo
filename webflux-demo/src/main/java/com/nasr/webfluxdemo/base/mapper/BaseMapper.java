package com.nasr.webfluxdemo.base.mapper;

import java.util.List;

public interface BaseMapper<E,D ,V > {

    D convertEntityToDto(E entity);
    E convertViewDtoToEntity(V viewDto);

    List<D> convertEntitiesToDtos(List<E> entities);
    List<E> convertViewDtosToEntities(List<V> dtoList);

}
