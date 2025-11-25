package org.xyz.CRUD.service;

import org.springframework.stereotype.Service;
import org.xyz.CRUD.entity.User;
import org.xyz.CRUD.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found with id: " + id));
    }

    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);

        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        if (user.getAge() != null) {
            existingUser.setAge(user.getAge());
        }
        if (user.getAddress() != null) {
            existingUser.setAddress(user.getAddress());
        }
        existingUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(existingUser);
    }


}