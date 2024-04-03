package com.pawith.domain.auth.service;

import com.pawith.domain.auth.entity.OAuth;
import com.pawith.domain.auth.exception.AuthError;
import com.pawith.domain.auth.exception.OAuthNotFoundException;
import com.pawith.domain.auth.repository.OAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthQueryService {
    private final OAuthRepository oAuthRepository;

    public boolean existBySub(final String sub) {
        return oAuthRepository.existsBySub(sub);
    }

    public OAuth findBySub(final String sub) {
        return oAuthRepository.findBySub(sub)
            .orElseThrow(() -> new OAuthNotFoundException(AuthError.OAUTH_NOT_FOUND));
    }

    public boolean existByUserId(final Long userId) {
        return oAuthRepository.existsByUserId(userId);
    }
}
