package org.xyz.CRUD.controller;

import org.springframework.web.bind.annotation.*;
import org.xyz.CRUD.entity.User;
import org.xyz.CRUD.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("get/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("create")
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("update/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("delete/{id}")
    public User delete(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
