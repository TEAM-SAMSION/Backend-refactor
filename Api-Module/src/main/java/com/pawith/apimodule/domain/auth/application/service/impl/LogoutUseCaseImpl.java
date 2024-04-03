package com.pawith.apimodule.domain.auth.application.service.impl;

import com.pawith.apimodule.domain.auth.application.service.LogoutUseCase;
import com.pawith.apimodule.domain.auth.application.utils.TokenExtractUtils;
import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.domain.auth.jwt.TokenType;
import com.pawith.domain.auth.service.TokenDeleteService;
import com.pawith.domain.auth.service.TokenQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@ApplicationService
@RequiredArgsConstructor
@Transactional
public class LogoutUseCaseImpl implements LogoutUseCase {

    private final TokenDeleteService tokenDeleteService;
    private final TokenQueryService tokenQueryService;

    @Override
    public void logoutAccessUser(String refreshTokenHeader) {
        final String refreshToken = TokenExtractUtils.extractToken(refreshTokenHeader);
        final Token refreshTokenEntity = tokenQueryService.findTokenByValue(refreshToken, TokenType.REFRESH_TOKEN);
        tokenDeleteService.deleteToken(refreshTokenEntity);
    }
}
