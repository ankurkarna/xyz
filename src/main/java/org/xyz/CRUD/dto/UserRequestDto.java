package org.xyz.CRUD.dto;

public record UserRequestDto(String username,
                             String password,
                             Integer age,
                             String address) {
}