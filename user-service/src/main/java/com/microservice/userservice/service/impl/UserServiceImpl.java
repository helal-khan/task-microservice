package com.microservice.userservice.service.impl;


import com.microservice.userservice.Exception.EntityNotFoundException;
import com.microservice.userservice.config.mapper.MapperRegistry;
import com.microservice.userservice.dto.UserDTO;
import com.microservice.userservice.entity.User;
import com.microservice.userservice.repository.UserRepository;
import com.microservice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperRegistry mapperRegistry;

    @Override
    public UserDTO add(final UserDTO userDTO) {
        final User savedUser = userRepository.save(mapperRegistry.getMapper(UserDTO.class, User.class).map(userDTO));
        return mapperRegistry.getMapper(User.class, UserDTO.class).map(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "user", key = "#id")
    public UserDTO get(final Long id) {
        return mapperRegistry.getMapper(User.class, UserDTO.class).map(findUser(id));
    }

    @Override
    @CachePut(cacheNames = "user", key = "#userDTO.id")
    public UserDTO update(final Long id, final UserDTO userDTO) {
        final User existingUser = findUser(id);
        existingUser.setName(userDTO.getName());
        return  mapperRegistry.getMapper(User.class, UserDTO.class).map(userRepository.save(existingUser));
    }

    @Override
    @CacheEvict(cacheNames = "user", key = "#id")
    public void delete(final Long id) {
        userRepository.delete(findUser(id));
    }

    private User findUser(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class));
    }
}
