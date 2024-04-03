package com.pawith.domain.todo.exception;

import com.pawith.commonmodule.exception.Error;

public class AssignNotFoundException extends com.pawith.tododomain.exception.TodoException {
    public AssignNotFoundException(Error error) {
        super(error);
    }
}
