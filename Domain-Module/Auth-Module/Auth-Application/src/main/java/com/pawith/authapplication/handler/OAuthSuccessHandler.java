package com.pawith.authapplication.handler;

import com.pawith.authapplication.handler.request.OAuthSuccessEvent;
import com.pawith.authdomain.entity.OAuth;
import com.pawith.authdomain.exception.AuthError;
import com.pawith.authdomain.exception.OAuthException;
import com.pawith.authdomain.service.OAuthQueryService;
import com.pawith.authdomain.service.OAuthSaveService;
import com.pawith.commonmodule.event.UserSignUpEvent;
import com.pawith.userdomain.entity.User;
import com.pawith.userdomain.service.user.UserReader;
import com.pawith.userdomain.service.user.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler {
    private final OAuthQueryService oAuthQueryService;
    private final OAuthSaveService oAuthSaveService;
    private final UserReader userReader;
    private final UserValidator userValidator;
    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener(OAuthSuccessEvent.class)
    @Transactional
    /**
     * TODO: 리팩터링,리팩터링,리팩터링,리팩터링,리팩터링,리팩터링,리팩터링,리팩터링,리팩터링,리팩터링,리팩터링
     */
    public void handle(OAuthSuccessEvent oAuthSuccessEvent) {
        if (oAuthQueryService.existBySub(oAuthSuccessEvent.sub())) {
            final OAuth oAuth = oAuthQueryService.findBySub(oAuthSuccessEvent.sub());
            final User user = userReader.findById(oAuth.getUserId());
            user.updateEmail(oAuthSuccessEvent.email());
        } else {
            if (userValidator.isEmailExist(oAuthSuccessEvent.email())) {
                final User user = userReader.findByEmail(oAuthSuccessEvent.email());
                if (oAuthQueryService.existByUserId(user.getId())|| user.isNotMatchingProvider(oAuthSuccessEvent.provider())) {
                    throw new OAuthException(AuthError.INVALID_OAUTH_REQUEST);
                }
                saveOAuth(oAuthSuccessEvent, user);
            } else {
                applicationEventPublisher.publishEvent(UserSignUpEvent.of(
                    oAuthSuccessEvent.username(),
                    oAuthSuccessEvent.email()
                ));
                final User user = userReader.findByEmail(oAuthSuccessEvent.email());
                saveOAuth(oAuthSuccessEvent, user);
            }
        }
    }

    private void saveOAuth(OAuthSuccessEvent oAuthSuccessEvent, User user) {
        OAuth oAuth = OAuth.of(
            oAuthSuccessEvent.provider(),
            oAuthSuccessEvent.sub(),
            user.getId()
        );
        oAuthSaveService.save(oAuth);
    }
}
