package com.pawith.apimodule.domain.auth.application.service.impl;

import com.pawith.apimodule.domain.auth.application.service.JWTExtractUserDetailsUseCase;
import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.domain.auth.jwt.JWTProvider;
import com.pawith.domain.auth.jwt.TokenType;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class JWTExtractUserDetailsUseCaseImpl implements JWTExtractUserDetailsUseCase<Long> {
    private final JWTProvider jwtProvider;

    @Override
    public Long extract(final String token) {
        return jwtProvider.extractUserClaimsFromToken(token, TokenType.ACCESS_TOKEN)
            .getUserId();
    }
}
