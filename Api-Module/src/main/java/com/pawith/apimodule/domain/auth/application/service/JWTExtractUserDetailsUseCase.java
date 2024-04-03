package com.pawith.apimodule.domain.auth.application.service;

public interface JWTExtractUserDetailsUseCase<T> {
    T extract(final String token);
}
