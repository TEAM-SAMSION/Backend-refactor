package com.pawith.apimodule.domain.todo.application.handler.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TodoDeleteEvent {
    private final Long todoId;
}
