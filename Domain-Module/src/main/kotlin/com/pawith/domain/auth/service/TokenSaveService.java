package com.pawith.domain.auth.service;


import com.pawith.domain.auth.jwt.TokenType;

public interface TokenSaveService {

    void saveToken(final String token, final TokenType tokenType, final Long userId);
}
