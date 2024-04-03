package com.pawith.domain.auth.service.impl;

import com.pawith.commonmodule.annotation.DomainService;
import com.pawith.domain.auth.entity.Token;
import com.pawith.domain.auth.repository.TokenRepository;
import com.pawith.domain.auth.service.TokenDeleteService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class TokenDeleteServiceImpl implements TokenDeleteService {

    private final TokenRepository tokenRepository;

    public void deleteToken(final Token token){
        tokenRepository.delete(token);
    }

    public void deleteAllToken(final List<Token> tokenList){
        tokenRepository.deleteAll(tokenList);
    }

    @Override
    public void deleteTokenByValue(String value) {
        tokenRepository.deleteByValue(value);
    }
}
