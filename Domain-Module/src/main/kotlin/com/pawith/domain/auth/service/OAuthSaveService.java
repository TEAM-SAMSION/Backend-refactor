package com.pawith.domain.auth.service;

import com.pawith.domain.auth.entity.OAuth;
import com.pawith.domain.auth.repository.OAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthSaveService {
    private final OAuthRepository oAuthRepository;

    public void save(final OAuth oAuth) {
        oAuthRepository.save(oAuth);
    }
}
