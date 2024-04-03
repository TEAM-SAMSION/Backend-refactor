package com.pawith.domain.auth.service.impl;

import com.pawith.commonmodule.annotation.DomainService;
import com.pawith.domain.auth.entity.Token;
import com.pawith.domain.auth.exception.AuthError;
import com.pawith.domain.auth.exception.NotExistTokenException;
import com.pawith.domain.auth.jwt.TokenType;
import com.pawith.domain.auth.repository.TokenRepository;
import com.pawith.domain.auth.service.TokenQueryService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TokenQueryServiceImpl implements TokenQueryService {
    private final TokenRepository tokenRepository;

    @Override
    public Token findTokenByValue(String value, TokenType tokenType) {
        return findToken(value, tokenType);
    }

    private Token findToken(final String value, final TokenType tokenType){
        return tokenRepository.findByValueAndTokenType(value, tokenType)
            .orElseThrow(() -> new NotExistTokenException(AuthError.NOT_EXIST_TOKEN));
    }


}
