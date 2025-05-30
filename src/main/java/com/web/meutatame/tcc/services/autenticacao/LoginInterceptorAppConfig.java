package com.web.meutatame.tcc.services.autenticacao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/login",
                        "/cadastro",
                        "/error",
                        "/logar",
                        "/img/**",
                        "/vendor/**",
                        "/js/**",
                        "/favicon.ico",
                        "/css/**"
                );
    }
}
