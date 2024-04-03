package com.pawith.apimodule.domain.todo.application.dto.response;

import com.pawith.tododomain.entity.CompletionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssignUserInfoResponse {
    private final Long assigneeId;
    private final String assigneeName;
    private final CompletionStatus completionStatus;
}
