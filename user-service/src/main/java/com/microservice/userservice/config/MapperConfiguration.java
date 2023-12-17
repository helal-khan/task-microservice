package com.microservice.userservice.config;

import com.microservice.userservice.config.mapper.MapperRegistry;
import com.microservice.userservice.config.mapper.impl.MapperRegistryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public MapperRegistry mapperRegistry() {
        return new MapperRegistryImpl();
    }
}