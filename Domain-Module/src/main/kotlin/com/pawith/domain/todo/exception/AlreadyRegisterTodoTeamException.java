package com.pawith.domain.todo.exception;

import com.pawith.commonmodule.exception.Error;

public class AlreadyRegisterTodoTeamException extends com.pawith.tododomain.exception.TodoException {
    public AlreadyRegisterTodoTeamException(Error error) {
        super(error);
    }
}
