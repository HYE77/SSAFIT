package com.ssafit.global.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ssafitOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SSAFIT API Documentation")
                        .description("SSAFIT REST API 명세서")
                        .version("1.0.0")
                		.license(new License().name("SSAFIT").url("https://www.ssafit.com")));
    }
}