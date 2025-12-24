package com.ssafit.domain.auth.service;

import com.ssafit.domain.auth.dto.AuthDto;

public interface AuthService {
    AuthDto.LoginResponse login(AuthDto.LoginRequest request);
}
