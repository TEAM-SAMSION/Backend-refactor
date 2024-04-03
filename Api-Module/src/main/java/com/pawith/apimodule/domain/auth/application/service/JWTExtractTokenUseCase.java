package com.pawith.apimodule.domain.auth.application.service;

public interface JWTExtractTokenUseCase {
    String extractToken(final String tokenHeader);
}
