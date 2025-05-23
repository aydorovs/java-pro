package org.product.mapper;

import org.product.entity.UserEntity;
import org.product.model.UserDto;
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