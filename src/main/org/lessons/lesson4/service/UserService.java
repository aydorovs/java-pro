package org.lessons.lesson4.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.lessons.lesson4.entity.UserEntity;
import org.lessons.lesson4.mapper.UserMapper;
import org.lessons.lesson4.model.UserDto;
import org.lessons.lesson4.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto createUser(String userName) {
        UserEntity userEntity = new UserEntity(userName);
        UserEntity saved = userRepository.save(userEntity);
        return userMapper.toUserDto(saved);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public UserDto getUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found. id = " + id));
        return userMapper.toUserDto(userEntity);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}