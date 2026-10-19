package com.ecommerce_bazar.services;

import com.ecommerce_bazar.dto.RegisterRequest;
import com.ecommerce_bazar.entities.User;

import java.util.Optional;

public interface UserService  {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    User saveUser(RegisterRequest registerRequest);
}


