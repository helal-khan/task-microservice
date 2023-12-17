package com.microservice.userservice.service.impl;


import com.microservice.userservice.Exception.EntityNotFoundException;
import com.microservice.userservice.config.mapper.MapperRegistry;
import com.microservice.userservice.dto.UserDTO;
import com.microservice.userservice.entity.User;
import com.microservice.userservice.event.UserEvent;
import com.microservice.userservice.event.UserEventType;
import com.microservice.userservice.repository.UserRepository;
import com.microservice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperRegistry mapperRegistry;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;
    private static final String topic = "USER_EVENTS";

    @Override
    public UserDTO add(final UserDTO userDTO) {
        final User savedUser = userRepository.save(mapperRegistry.getMapper(UserDTO.class, User.class).map(userDTO));

        kafkaTemplate.send(topic,
                UserEvent.builder()
                        .userId(savedUser.getId())
                        .eventType(UserEventType.USER_CREATED)
                        .build()
        );

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
        final User updatedUser = userRepository.save(existingUser);

        kafkaTemplate.send(topic,
                UserEvent.builder()
                        .userId(updatedUser.getId())
                        .eventType(UserEventType.USER_UPDATED)
                        .build()
        );

        return mapperRegistry.getMapper(User.class, UserDTO.class).map(updatedUser);
    }

    @Override
    @CacheEvict(cacheNames = "user", key = "#id")
    public void delete(final Long id) {
        final User user = findUser(id);
        userRepository.delete(user);

        kafkaTemplate.send(topic,
                UserEvent.builder()
                        .userId(user.getId())
                        .eventType(UserEventType.USER_DELETED)
                        .build()
        );
    }

    private User findUser(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class));
    }
}
