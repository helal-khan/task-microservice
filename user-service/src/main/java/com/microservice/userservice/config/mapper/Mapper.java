package com.microservice.userservice.config.mapper;

@FunctionalInterface
public interface Mapper<S, T> {
    T map(S source);
}
