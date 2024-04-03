package com.pawith.domain.todo.exception;

import com.pawith.commonmodule.exception.Error;

public class TodoModificationNotAllowedException extends TodoException {
    public TodoModificationNotAllowedException(Error error) {
        super(error);
    }
}
