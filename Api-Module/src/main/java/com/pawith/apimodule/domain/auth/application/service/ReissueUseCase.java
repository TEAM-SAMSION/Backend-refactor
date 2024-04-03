package com.pawith.apimodule.domain.auth.application.service;

import com.pawith.apimodule.domain.auth.application.dto.TokenReissueResponse;

public interface ReissueUseCase {
    TokenReissueResponse reissue(String refreshToken);
}
