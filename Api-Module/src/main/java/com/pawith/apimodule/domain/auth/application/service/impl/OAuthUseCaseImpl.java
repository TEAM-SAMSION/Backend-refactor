package com.pawith.apimodule.domain.auth.application.service.impl;

import com.pawith.apimodule.domain.auth.application.dto.OAuthRequest;
import com.pawith.apimodule.domain.auth.application.dto.OAuthResponse;
import com.pawith.apimodule.domain.auth.application.service.OAuthUseCase;
import com.pawith.apimodule.domain.auth.application.service.oauth.OAuthInvoker;
import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.commonmodule.enums.Provider;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class OAuthUseCaseImpl implements OAuthUseCase {

    private final OAuthInvoker oAuthInvoker;

    @Override
    public OAuthResponse oAuthLogin(Provider provider, String accessToken){
        return oAuthInvoker.execute(new OAuthRequest(provider, accessToken));
    }
}
