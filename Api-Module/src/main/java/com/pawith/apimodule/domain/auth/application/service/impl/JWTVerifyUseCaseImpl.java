package com.pawith.apimodule.domain.auth.application.service.impl;

import com.pawith.apimodule.domain.auth.application.service.JWTVerifyUseCase;
import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.domain.auth.jwt.JWTProvider;
import com.pawith.domain.auth.jwt.TokenType;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class JWTVerifyUseCaseImpl implements JWTVerifyUseCase {
    private final JWTProvider jwtProvider;

    @Override
    public void validateToken(final String token){
        jwtProvider.validateToken(token, TokenType.ACCESS_TOKEN);
    }
}
