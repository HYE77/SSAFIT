package com.ssafit.domain.auth.dto;

import lombok.*;

public class AuthDto {

    // 로그인 요청
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        private String loginId;
        private String pwd;
    }

    // 로그인 응답
    @Getter
    @AllArgsConstructor
    public static class LoginResponse {
        private String accessToken;
        private String tokenType = "Bearer";

        public LoginResponse(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
