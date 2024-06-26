package com.pawith.apimodule.domain.todo.application.dto.response;

import com.pawith.tododomain.entity.Authority;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TodoTeamNameResponse {
    private Long teamId;
    private String teamName;
    private Authority authority;
}
