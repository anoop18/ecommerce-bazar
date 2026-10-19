package com.ecommerce_bazar.services;

import com.ecommerce_bazar.dto.LoginRequest;
import com.ecommerce_bazar.dto.TokenResponse;

public interface AuthService {
    TokenResponse login(LoginRequest loginRequest);

}
