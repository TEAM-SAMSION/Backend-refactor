package com.pawith.apimodule.domain.auth.application.service.oauth.impl;

import com.pawith.apimodule.domain.auth.application.dto.OAuthRequest;
import com.pawith.apimodule.domain.auth.application.dto.OAuthUserInfo;
import com.pawith.apimodule.domain.auth.application.service.oauth.AuthHandler;
import com.pawith.apimodule.domain.auth.application.service.oauth.feign.NaverOAuthFeignClient;
import com.pawith.apimodule.domain.auth.application.service.oauth.feign.response.NaverUserInfo;
import com.pawith.commonmodule.enums.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaverOAuthHandler implements AuthHandler {
    private static final Provider OAUTH_TYPE = Provider.NAVER;
    private static final String NAVER_AUTHORIZATION_BEARER = "Bearer ";

    private final NaverOAuthFeignClient naverOAuthFeignClient;

    @Override
    public OAuthUserInfo handle(OAuthRequest authenticationInfo) {
        final NaverUserInfo naverUserInfo = getNaverUserInfo(authenticationInfo.getAccessToken());
        return new OAuthUserInfo(naverUserInfo.getNickname(), naverUserInfo.getEmail(), null);
    }

    @Override
    public boolean isAccessible(OAuthRequest authInfo) {
        return OAUTH_TYPE.equals(authInfo.getProvider());
    }

    private NaverUserInfo getNaverUserInfo(String accessToken){
        return naverOAuthFeignClient.getNaverUserInfo(NAVER_AUTHORIZATION_BEARER+accessToken);
    }
}
