package com.pawith.apimodule.domain.auth.application.service.oauth;

import com.pawith.apimodule.domain.auth.application.consts.AuthConsts;
import com.pawith.apimodule.domain.auth.application.dto.OAuthRequest;
import com.pawith.apimodule.domain.auth.application.dto.OAuthResponse;
import com.pawith.apimodule.domain.auth.application.dto.OAuthUserInfo;
import com.pawith.apimodule.domain.auth.application.exception.AuthException;
import com.pawith.apimodule.domain.auth.application.handler.request.OAuthSuccessEvent;
import com.pawith.domain.auth.exception.AuthError;
import com.pawith.domain.auth.jwt.JWTProvider;
import com.pawith.domain.auth.jwt.PrivateClaims;
import com.pawith.domain.auth.jwt.TokenType;
import com.pawith.domain.auth.service.OAuthQueryService;
import com.pawith.domain.auth.service.TokenSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuthInvoker {
    private final List<AuthHandler> authHandlerList;
    private final JWTProvider jwtProvider;
    private final TokenSaveService tokenSaveService;
    private final ApplicationEventPublisher publisher;
    private final OAuthQueryService oAuthQueryService;

    public OAuthResponse execute(OAuthRequest request) {
        final OAuthUserInfo oAuthUserInfo = attemptLogin(request);
        publishEvent(oAuthUserInfo, request);
        return generateServerAuthenticationTokens(
            PrivateClaims.UserClaims.of(oAuthQueryService.findBySub(oAuthUserInfo.getSub()).getUserId())
        );
    }

    private void publishEvent(OAuthUserInfo oAuthUserInfo, OAuthRequest request) {
        publisher.publishEvent(OAuthSuccessEvent.of(
            oAuthUserInfo.getUsername(),
            oAuthUserInfo.getEmail(),
            request.getProvider(),
            oAuthUserInfo.getSub()
        ));
    }

    private OAuthUserInfo attemptLogin(OAuthRequest request) {
        for (AuthHandler authHandler : authHandlerList) {
            if (authHandler.isAccessible(request)) {
                return authHandler.handle(request);
            }
        }
        throw new AuthException(AuthError.OAUTH_FAIL);
    }

    private OAuthResponse generateServerAuthenticationTokens(PrivateClaims.UserClaims userClaims) {
        final JWTProvider.Token token = jwtProvider.generateToken(userClaims);
        tokenSaveService.saveToken(token.refreshToken(), TokenType.REFRESH_TOKEN, userClaims.getUserId());
        final String accessToken = AuthConsts.AUTHENTICATION_TYPE_PREFIX + token.accessToken();
        final String refreshToken = AuthConsts.AUTHENTICATION_TYPE_PREFIX + token.refreshToken();
        return OAuthResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }
}
