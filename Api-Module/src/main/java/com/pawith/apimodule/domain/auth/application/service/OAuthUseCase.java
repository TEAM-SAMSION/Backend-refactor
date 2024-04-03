package com.pawith.apimodule.domain.auth.application.service;

import com.pawith.apimodule.domain.auth.application.dto.OAuthResponse;
import com.pawith.commonmodule.enums.Provider;

public interface OAuthUseCase {

    OAuthResponse oAuthLogin(Provider provider, String accessToken);

}
