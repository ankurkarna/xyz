package org.xyz.CRUD.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xyz.CRUD.dto.UserRequestDto;
import org.xyz.CRUD.dto.UserResponseDto;
import org.xyz.CRUD.entity.User;
import org.xyz.CRUD.exception.ApiRequestException;
import org.xyz.CRUD.mapper.UserMapper;
import org.xyz.CRUD.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = UserMapper.toUserEntity(userRequestDto);
        String hashPassword = passwordEncoder.encode(userRequestDto.password());
        user.setPassword(hashPassword);
        User save = userRepository.save(user);
        return UserMapper.toUserResponseDto(save);
    }

    public UserResponseDto getUserById(Long id) {
        return UserMapper.toUserResponseDto(userRepository
                .findById(id)
                .orElseThrow(() -> new ApiRequestException("user not found with id : " + id)));
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found with id : " + id));
        UserMapper.updateUser(userRequestDto, existing);
        User saved = userRepository.save(existing);
        return UserMapper.toUserResponseDto(saved);
    }

    public UserResponseDto deleteUser(Long id) {
        UserResponseDto user = getUserById(id);
        userRepository.deleteById(id);
        return user;
    }


}