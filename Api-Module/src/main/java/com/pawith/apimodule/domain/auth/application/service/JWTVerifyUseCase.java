package com.pawith.apimodule.domain.auth.application.service;

public interface JWTVerifyUseCase {
    void validateToken(final String token);
}
