package com.pawith.apimodule.domain.todo.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TodoTeamCreateRequest{
    private String teamName;
    private String randomCode;
    private String description;
    private List<PetRegisterRequest> petRegisters;
}

