package com.pawith.apimodule.domain.auth.application.exception;

import com.pawith.commonmodule.exception.BusinessException;
import com.pawith.commonmodule.exception.Error;

public class AuthException extends BusinessException {
    public AuthException(Error error) {
        super(error);
    }
}
