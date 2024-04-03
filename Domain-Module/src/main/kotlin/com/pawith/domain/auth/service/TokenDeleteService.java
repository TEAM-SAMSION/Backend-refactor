package com.pawith.domain.auth.service;


import com.pawith.domain.auth.entity.Token;

import java.util.List;

public interface TokenDeleteService {

    void deleteToken(final Token token);

    void deleteAllToken(final List<Token> tokenList);

    void deleteTokenByValue(final String value);
}
