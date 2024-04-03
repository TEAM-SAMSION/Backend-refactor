package com.pawith.domain.todo.exception;

import com.pawith.commonmodule.exception.Error;
public class NotRegisterUserException extends com.pawith.tododomain.exception.TodoException {
    public NotRegisterUserException(Error error) {
        super(error);
    }
}
