package com.pawith.commonmodule.event;

import lombok.Getter;

public class ChristmasEvent {

    public record ChristmasEventCreateNewTodoTeam(Long todoTeamId) {
    }

    public record ChristmasEventCreateNewRegister(Long todoTeamId, Long userId) {
    }

}
