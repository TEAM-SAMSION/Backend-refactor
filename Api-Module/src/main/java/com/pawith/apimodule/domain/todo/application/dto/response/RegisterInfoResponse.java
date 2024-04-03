package com.pawith.apimodule.domain.todo.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterInfoResponse {
    private final Long registerId;
    private final String registerName;
}
