package com.pawith.apimodule.domain.auth.application.handler;

import com.pawith.commonmodule.event.UserAccountDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthRevokeHandler {

    @EventListener
    public void revokeOAuthUser(UserAccountDeleteEvent event){
        // 사용자 탈퇴시 각 OAuth refreshToken을 통해 탈퇴 처리
        // 사용자 조회
        // 사용자의 OAuth 정보 조회
        // OAuth refreshToken을 통해 탈퇴 처리

        // OAuth refreshToken이 없으면 재로그인 처리해서 refreshToken을 발급받도록 처리
    }
}
