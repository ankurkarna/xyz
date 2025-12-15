package org.xyz.CRUD.mapper;

import org.xyz.CRUD.dto.UserRequestDto;
import org.xyz.CRUD.dto.UserResponseDto;
import org.xyz.CRUD.entity.User;

public class UserMapper {

    public static User toUserEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setAge(userRequestDto.age());
        user.setAddress(userRequestDto.address());
        user.setStatus("Active");
        return user;
    }

    public static UserResponseDto toUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getAddress(),
                user.getAge(),
                user.getCreatedAt(),
                user.getUpdatedAt());
        return userResponseDto;
    }

    public static void updateUser(UserRequestDto userRequestDto, User user) {
        if (userRequestDto.username() != null) {
            user.setUsername(userRequestDto.username());
        }
        if (userRequestDto.password() != null) {
            user.setPassword(userRequestDto.password());
        }
        if (userRequestDto.age() != null) {
            user.setAge(userRequestDto.age());
        }
        if (userRequestDto.address() != null) {
            user.setAddress(userRequestDto.address());
        }
    }

}
