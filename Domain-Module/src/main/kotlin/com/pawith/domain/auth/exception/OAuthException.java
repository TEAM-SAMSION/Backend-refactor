package com.pawith.domain.auth.exception;

import com.pawith.commonmodule.exception.BusinessException;
import com.pawith.commonmodule.exception.Error;

public class OAuthException extends BusinessException {
    public OAuthException(Error error) {
        super(error);
    }
}
