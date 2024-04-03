package com.pawith.domain.auth.jwt.exception;

import com.pawith.commonmodule.exception.Error;

public class ExpiredTokenException extends TokenException {
    public ExpiredTokenException(Error error) {
        super(error);
    }
}
