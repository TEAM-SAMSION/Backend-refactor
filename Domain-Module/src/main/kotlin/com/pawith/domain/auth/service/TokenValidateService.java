package com.pawith.domain.auth.service;

import com.pawith.commonmodule.annotation.DomainService;
import com.pawith.domain.auth.exception.AuthError;
import com.pawith.domain.auth.exception.NotExistTokenException;
import com.pawith.domain.auth.jwt.TokenType;
import com.pawith.domain.auth.repository.TokenRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TokenValidateService {
    private final TokenRepository tokenRepository;

    public void validateIsExistToken(final String token, final TokenType tokenType) {
        if(!tokenRepository.existsByValueAndTokenType(token, tokenType)){
            throw new NotExistTokenException(AuthError.NOT_EXIST_TOKEN);
        }
    }
}
