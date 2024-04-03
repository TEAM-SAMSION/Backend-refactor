package com.pawith.apimodule.domain.todo.application.dto.response;

import lombok.Getter;

@Getter
public class TodoProgressResponse {

    private final Integer progress;

    public TodoProgressResponse(Integer progress) {
        this.progress = progress;
    }
}
