package com.ecommerce_bazar.controllers;

import com.ecommerce_bazar.dto.LoginRequest;
import com.ecommerce_bazar.dto.RegisterRequest;
import com.ecommerce_bazar.dto.TokenResponse;
import com.ecommerce_bazar.entities.User;
import com.ecommerce_bazar.services.AuthService;
import com.ecommerce_bazar.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;



    public AuthController(UserService userService,AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody RegisterRequest request) {
        String email = request.email().trim().toLowerCase(Locale.ROOT);
        if (userService.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }
        User saved = userService.saveUser(request);
        return Map.of("id", saved.getId(), "email", saved.getEmail());
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
