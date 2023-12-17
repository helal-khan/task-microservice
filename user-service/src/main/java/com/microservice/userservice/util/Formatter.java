package com.microservice.userservice.util;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import java.util.stream.Collectors;

public class Formatter {
    public static String getErrors(@Nonnull final BindingResult result) {
        return result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
    }
}