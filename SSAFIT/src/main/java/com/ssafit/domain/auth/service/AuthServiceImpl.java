package com.ssafit.domain.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafit.domain.auth.dto.AuthDto;
import com.ssafit.domain.user.dao.UserDao;
import com.ssafit.domain.user.entity.User;
import com.ssafit.global.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthDto.LoginResponse login(AuthDto.LoginRequest request) {

    	// 1. login Id로 사용자 조회
        User user = userDao.selectUserbyId(request.getLoginId());

        if (user == null) {
            throw new RuntimeException("일치하는 회원 정보가 없습니다.");
        }
        
        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(request.getPwd(), user.getPwd())) {
        	throw new RuntimeException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        // 3. JWT 생성 (loginId를 subject로)
        String token = jwtTokenProvider.createToken(user.getLoginId(), user.getTokenVersion());

        return new AuthDto.LoginResponse(token);
    }
}
