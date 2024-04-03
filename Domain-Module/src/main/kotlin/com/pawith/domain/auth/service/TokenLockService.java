package com.pawith.domain.auth.service;

import com.pawith.commonmodule.annotation.DomainService;
import com.pawith.domain.auth.repository.TokenLockRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TokenLockService {

    private final TokenLockRepository tokenLockRepository;

    public void lockToken(final String token){
        tokenLockRepository.getNamedLock(token);
    }

    public void releaseLockToken(final String token){
        tokenLockRepository.releaseNamedLock(token);
    }
}
