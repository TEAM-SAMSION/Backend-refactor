package com.pawith.domain.auth.exception;

import com.pawith.commonmodule.exception.Error;

public class OAuthNotFoundException extends OAuthException{
    public OAuthNotFoundException(Error error) {
        super(error);
    }
}
