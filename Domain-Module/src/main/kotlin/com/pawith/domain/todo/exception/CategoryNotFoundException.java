package com.pawith.domain.todo.exception;

import com.pawith.commonmodule.exception.Error;
public class CategoryNotFoundException extends com.pawith.tododomain.exception.TodoException {
    public CategoryNotFoundException(Error error) {
        super(error);
    }
}
