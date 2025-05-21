package org.lessons.lesson4.mapper;

import org.lessons.lesson4.entity.UserEntity;
import org.lessons.lesson4.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(UserEntity userEntity) {
        return UserDto
                .builder()
                .id(userEntity.getId())
                .userName(userEntity.getUserName())
                .build();
    }
}