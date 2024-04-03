package com.pawith.domain.todo.exception;

import com.pawith.commonmodule.exception.Error;
public class TodoNotFoundException extends TodoException {
    public TodoNotFoundException(Error error) { super(error); }
}
