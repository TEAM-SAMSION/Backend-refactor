package com.pawith.apimodule.domain.auth.presentation;

import com.pawith.apimodule.domain.auth.application.consts.AuthConsts;
import com.pawith.apimodule.domain.auth.application.dto.OAuthResponse;
import com.pawith.apimodule.domain.auth.application.dto.TokenReissueResponse;
import com.pawith.apimodule.domain.auth.application.service.LogoutUseCase;
import com.pawith.apimodule.domain.auth.application.service.OAuthUseCase;
import com.pawith.apimodule.domain.auth.application.service.ReissueUseCase;
import com.pawith.commonmodule.enums.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthUseCase oAuthUseCase;
    private final LogoutUseCase logoutUseCase;
    private final ReissueUseCase reissueUseCase;

    @GetMapping("/oauth/{provider}")
    public OAuthResponse oAuthLogin(@PathVariable Provider provider,
                                    @RequestHeader("Authorization") String accessToken
                                    ){
        return oAuthUseCase.oAuthLogin(provider, accessToken);
    }

    @PostMapping("/reissue")
    public TokenReissueResponse reissue(@RequestHeader(AuthConsts.REFRESH_TOKEN_HEADER) String refreshToken){
        return reissueUseCase.reissue(refreshToken);
    }


    @DeleteMapping("/logout")
    public void logout(@RequestHeader(AuthConsts.REFRESH_TOKEN_HEADER) String refreshToken){
        logoutUseCase.logoutAccessUser(refreshToken);
    }

}
