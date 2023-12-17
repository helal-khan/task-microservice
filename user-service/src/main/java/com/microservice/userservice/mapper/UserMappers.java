package com.microservice.userservice.mapper;

import com.microservice.userservice.config.mapper.Mapper;
import com.microservice.userservice.config.mapper.MapperRegistry;
import com.microservice.userservice.dto.UserDTO;
import com.microservice.userservice.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMappers {

    private final MapperRegistry mapperRegistry;

    @PostConstruct
    private void registerMappers() {
        mapperRegistry.addMapper(User.class, UserDTO.class, userToUserDTOMapper());
        mapperRegistry.addMapper(UserDTO.class, User.class, userDTOToUserMapper());
    }

    private Mapper<User, UserDTO> userToUserDTOMapper() {
        return user -> UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    private Mapper<UserDTO, User> userDTOToUserMapper() {
        return userDTO -> User.builder()
                .name(userDTO.getName())
                .build();
    }
}
