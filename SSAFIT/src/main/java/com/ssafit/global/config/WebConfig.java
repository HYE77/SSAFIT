package com.ssafit.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 현재 SSAFIT 프로젝트에는 Interceptor 구조가 정해지지 않았기 때문에 
    // 아래 Interceptor 관련 부분은 확실하지 않아 주석 처리하거나 확장 시 사용 가능.
    /*
    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/admin/**");
    }
    */

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                // Vue 개발 서버(localhost:5173 기준)
                .allowedOrigins(
                        "http://localhost:5173",
                        "http://127.0.0.1:5173"
                )
                // 여러 메서드 허용 (REST API)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                // 모든 헤더 허용
                .allowedHeaders("*")
                // 프론트에서 쿠키/Authorization 헤더 보내려면 필요
                .allowCredentials(true)
                // preflight 캐시 시간
                .maxAge(3600);
    }
}
