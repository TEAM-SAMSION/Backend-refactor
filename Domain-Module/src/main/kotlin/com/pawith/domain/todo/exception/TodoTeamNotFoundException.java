package com.pawith.domain.todo.exception;

import com.pawith.commonmodule.exception.Error;
public class TodoTeamNotFoundException extends TodoException{
    public TodoTeamNotFoundException(Error error) {
        super(error);
    }
}
