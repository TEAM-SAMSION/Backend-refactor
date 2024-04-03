package com.pawith.apimodule.domain.auth.application.service;

public interface LogoutUseCase {

    void logoutAccessUser(String refreshTokenHeader);
}
