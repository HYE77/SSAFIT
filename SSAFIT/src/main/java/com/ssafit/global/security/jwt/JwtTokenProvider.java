package com.ssafit.global.security.jwt;

import java.security.Key;
import java.util.Date;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key key;

    private static final long EXPIRATION = 1000L * 60 * 60 * 24; // 1일

    // secretKey 초기화
    @PostConstruct
    protected void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // ============================================================
    // JWT 생성
    // ============================================================
    public String createToken(String loginId, int tokenVersion) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION);

        return Jwts.builder()
                .setSubject(loginId)
                .claim("tokenVersion", tokenVersion)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    // ============================================================
    // JWT token 버전 추출
    // ============================================================
    public int getTokenVersion(String token) {
        return parseClaims(token).get("tokenVersion", Integer.class);
    }


    // ============================================================
    // JWT에서 loginId 추출
    // ============================================================
    public String getLoginId(String token) {
        return parseClaims(token).getSubject();
    }

    // ============================================================
    // JWT 유효성 검증
    // ============================================================
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            // 토큰 만료
        } catch (JwtException | IllegalArgumentException e) {
            // 위조, 형식 오류
        }
        return false;
    }

    // ============================================================
    // Claims 파싱
    // ============================================================
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
