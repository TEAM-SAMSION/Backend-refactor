package com.pawith.domain.auth.service;


import com.pawith.domain.auth.entity.Token;
import com.pawith.domain.auth.jwt.TokenType;

public interface TokenQueryService {

    Token findTokenByValue(final String value, final TokenType tokenType);
}
