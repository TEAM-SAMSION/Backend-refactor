package com.pawith.apimodule.domain.auth.presentation.common.security;

import com.pawith.apimodule.domain.auth.presentation.common.security.filter.JWTAuthenticationFilter;
import com.pawith.apimodule.domain.auth.presentation.common.security.filter.JWTEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class SecurityHandler {
    private static JWTAuthenticationFilter jwtAuthenticationFilter;
    private static JWTEntryPoint jwtEntryPoint;

    public SecurityHandler(JWTAuthenticationFilter jwtAuthenticationFilter, JWTEntryPoint jwtEntryPoint) {
        SecurityHandler.jwtAuthenticationFilter = jwtAuthenticationFilter;
        SecurityHandler.jwtEntryPoint = jwtEntryPoint;
    }

    public static JWTAuthenticationFilter getJWTAuthenticationFilter(){
        return jwtAuthenticationFilter;
    }

    public static JWTEntryPoint getJWTEntryPoint(){
        return jwtEntryPoint;
    }
}
