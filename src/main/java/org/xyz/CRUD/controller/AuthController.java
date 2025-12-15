package org.xyz.CRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xyz.CRUD.dto.AuthRequest;
import org.xyz.CRUD.dto.LoginResponse;
import org.xyz.CRUD.entity.User;
import org.xyz.CRUD.repository.UserRepository;
import org.xyz.CRUD.utils.JwtUtil;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody AuthRequest request) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        if (authentication.isAuthenticated()) {
            User user = userRepository.findByUsername(request.getUsername());
            String token = jwtUtil.generateToken(request.getUsername());
            return new LoginResponse(token, user);
        } else {
            throw new RuntimeException("Invalid login");
        }
    }
}
