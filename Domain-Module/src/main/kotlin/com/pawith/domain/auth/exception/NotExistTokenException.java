package com.pawith.domain.auth.exception;

import com.pawith.commonmodule.exception.Error;
import com.pawith.domain.auth.jwt.exception.TokenException;

public class NotExistTokenException extends TokenException {
    public NotExistTokenException(Error error) {
        super(error);
    }
}
