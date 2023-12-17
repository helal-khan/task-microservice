package com.microservice.userservice.service;


import com.microservice.userservice.dto.UserDTO;
import com.microservice.userservice.entity.User;


public interface UserService {

    UserDTO add(final UserDTO userDTO);

    UserDTO get(final Long id);

    UserDTO update(final Long id, final UserDTO userDTO);

    void delete(final Long id);
}
