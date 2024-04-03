package com.pawith.domain.todo.exception;

import com.pawith.commonmodule.exception.Error;
public class UnregistrableException extends TodoException{
    public UnregistrableException(Error error) {
        super(error);
    }
}
