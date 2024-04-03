package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.apimodule.domain.todo.application.dto.response.TodoTeamRandomCodeResponse;
import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.tododomain.service.TodoTeamCodeManageService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class TodoTeamRandomCodeGetUseCase {

    private final TodoTeamCodeManageService todoTeamCodeManageService;

    public TodoTeamRandomCodeResponse generateRandomCode() {
        return new TodoTeamRandomCodeResponse(todoTeamCodeManageService.generateRandomCode());
    }
}
