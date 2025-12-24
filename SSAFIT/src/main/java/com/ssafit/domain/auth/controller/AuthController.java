package com.ssafit.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.domain.auth.dto.AuthDto;
import com.ssafit.domain.auth.service.AuthService;
import com.ssafit.domain.user.dao.UserDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth API", description = "인증 관련 API")
public class AuthController {

    private final AuthService authService;
    private final UserDao userDao;

    // ============================================================
    // 로그인
    // ============================================================
    @Operation(
            summary = "로그인",
            description = "JWT 기반 로그인"
        )
    @PostMapping("/login")
    public ResponseEntity<AuthDto.LoginResponse> login(
            @RequestBody AuthDto.LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    
    // ============================================================
    // 로그아웃
    // ============================================================
    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "토큰을 즉시 무효화합니다.")
    public ResponseEntity<Void> logout(
            @RequestAttribute("userId") Long userId
    ) {
        userDao.increaseTokenVersion(userId);
        return ResponseEntity.ok().build();
    }

}
