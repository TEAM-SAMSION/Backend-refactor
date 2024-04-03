package com.pawith.domain.todo.exception;

import com.pawith.commonmodule.exception.Error;
public class UnchangeableException extends TodoException {

        public UnchangeableException(Error error) {
            super(error);
        }
}
