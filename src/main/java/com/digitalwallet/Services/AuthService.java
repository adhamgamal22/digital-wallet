package com.digitalwallet.Services;

import com.digitalwallet.dtos.LoginRequest;
import com.digitalwallet.dtos.RegisterRequest;
import com.digitalwallet.dtos.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(String refreshToken);

    void logout(String token);

    void validateToken(String token);
}