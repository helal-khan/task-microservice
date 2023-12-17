package com.microservice.userservice.controller;


import com.microservice.userservice.dto.UserDTO;
import com.microservice.userservice.entity.User;
import com.microservice.userservice.service.UserService;
import com.microservice.userservice.util.Formatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.microservice.userservice.validator.UserValidator;

import javax.validation.ValidationException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserDTO userDTO, BindingResult result) {
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            throw new ValidationException(Formatter.getErrors(result));
        }
        return userService.add(userDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO get(@PathVariable Long id) {
        return userService.get(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO, BindingResult result) {
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            throw new ValidationException(Formatter.getErrors(result));
        }
        return userService.update(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}