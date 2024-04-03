package com.pawith.apimodule.domain.auth.application.service.oauth;


import com.pawith.apimodule.domain.auth.application.dto.OAuthRequest;
import com.pawith.apimodule.domain.auth.application.dto.OAuthUserInfo;

public interface AuthHandler{
    OAuthUserInfo handle(OAuthRequest authenticationInfo);

    default boolean isAccessible(OAuthRequest authenticationInfo){
        return false;
    }
}
