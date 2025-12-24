package com.ssafit.global.security.jwt;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssafit.domain.user.dao.UserDao;
import com.ssafit.domain.user.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDao userDao;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = resolveToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String loginId = jwtTokenProvider.getLoginId(token);
            
            User user = userDao.selectUserbyId(loginId);
            int tokenVersionInToken = jwtTokenProvider.getTokenVersion(token);
            
            if (user == null || user.getTokenVersion() != tokenVersionInToken) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            
            if (user != null) {
            	request.setAttribute("loginId", loginId);
            	request.setAttribute("userId", user.getId());
            	
            	// 핵심: Authentication 생성
            	Authentication authentication =
            			new UsernamePasswordAuthenticationToken(
            					loginId,        // principal
            					null,           // credentials
            					Collections.emptyList() // 권한 (추후 ROLE 추가 가능)
            					);
            	
            	// SecurityContext에 저장
            	SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
