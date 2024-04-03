package com.pawith.domain.auth.service.impl;

import com.pawith.commonmodule.annotation.DomainService;
import com.pawith.domain.auth.entity.Token;
import com.pawith.domain.auth.jwt.TokenType;
import com.pawith.domain.auth.repository.TokenRepository;
import com.pawith.domain.auth.service.TokenSaveService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TokenSaveServiceImpl implements TokenSaveService {
    private final TokenRepository tokenRepository;

    @Override
    public void saveToken(String token, TokenType tokenType, Long userId) {
        final Token saveToken = Token.builder()
            .tokenType(tokenType)
            .value(token)
            .userId(userId)
            .build();
        tokenRepository.save(saveToken);
    }
}
