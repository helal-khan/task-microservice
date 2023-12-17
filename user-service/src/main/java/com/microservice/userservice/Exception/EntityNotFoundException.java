package com.microservice.userservice.Exception;

import lombok.Getter;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.io.Serializable;

@Getter
public class EntityNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 1338734201687057050L;

    private final Class<? extends Serializable> clazz;

    public EntityNotFoundException(Class<? extends Serializable> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getMessage() {
        return clazz.getSimpleName() + " not found";
    }
}
