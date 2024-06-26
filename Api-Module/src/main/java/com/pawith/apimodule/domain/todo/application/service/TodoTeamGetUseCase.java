package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.apimodule.domain.todo.application.mapper.TodoTeamMapper;
import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.commonmodule.response.ListResponse;
import com.pawith.commonmodule.response.SliceResponse;
import com.pawith.todoapplication.dto.response.*;
import com.pawith.tododomain.entity.Register;
import com.pawith.tododomain.entity.TodoTeam;
import com.pawith.tododomain.service.PetQueryService;
import com.pawith.tododomain.service.RegisterQueryService;
import com.pawith.tododomain.service.TodoTeamQueryService;
import com.pawith.domain.User;
import com.pawith.domain.UserUtils;
import com.pawith.domain.service.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoTeamGetUseCase {

    private final UserUtils userUtils;
    private final RegisterQueryService registerQueryService;
    private final TodoTeamQueryService todoTeamQueryService;
    private final UserReader userReader;
    private final PetQueryService petQueryService;


    public SliceResponse<TodoTeamInfoResponse> getTodoTeams(final Pageable pageable) {
        final User requestUser = userUtils.getAccessUser();
        final Slice<TodoTeamInfoResponse> todoTeamSimpleResponses =
            registerQueryService.findRegisterSliceByUserId(requestUser.getId(), pageable)
                .map(register -> TodoTeamMapper.mapToTodoTeamSimpleResponse(register.getTodoTeam(), register));
        return SliceResponse.from(todoTeamSimpleResponses);
    }

    public ListResponse<TodoTeamNameResponse> getTodoTeamName() {
        final User requestUser = userUtils.getAccessUser();
        final List<TodoTeamNameResponse> todoTeamNameResponses =
            registerQueryService.findRegisterListByUserIdWithTodoTeam(requestUser.getId())
                .stream()
                .map(register -> TodoTeamMapper.mapToTodoTeamNameResponse(register.getTodoTeam(), register))
                .toList();
        return ListResponse.from(todoTeamNameResponses);
    }

    public TodoTeamSearchInfoResponse searchTodoTeamByCode(final String code) {
        final TodoTeam todoTeam = todoTeamQueryService.findTodoTeamByCode(code);
        final Register presidentRegister = registerQueryService.findPresidentRegisterByTodoTeamId(todoTeam.getId());
        final User presidentUser = userReader.readByUserId(presidentRegister.getUserId());
        final Integer registerCount = registerQueryService.countRegisterByTodoTeamId(todoTeam.getId());
        return TodoTeamMapper.mapToTodoTeamSearchInfoResponse(todoTeam, presidentUser, registerCount);
    }

    public TodoTeamRandomCodeResponse getTodoTeamCode(final Long teamId) {
        final TodoTeam todoTeam = todoTeamQueryService.findTodoTeamById(teamId);
        return TodoTeamMapper.mapToTodoTeamRandomCodeResponse(todoTeam);
    }

    public ListResponse<WithdrawTodoTeamResponse> getWithdrawTodoTeam() {
        final User requestUser = userUtils.getAccessUser();
        final List<TodoTeam> todoTeamList = todoTeamQueryService.findAllTodoTeamByUserId(requestUser.getId());
        return ListResponse.from(
            todoTeamList.stream()
                .map(todoTeam -> new WithdrawTodoTeamResponse(todoTeam.getImageUrl(), todoTeam.getTeamName()))
                .toList()
        );
    }

    public TodoTeamInfoDetailResponse getTodoTeamInfo(final Long todoTeamId) {
        final TodoTeam todoTeam = todoTeamQueryService.findTodoTeamById(todoTeamId);
        final Integer registerCount = registerQueryService.countRegisterByTodoTeamId(todoTeamId);
        final Integer petCount = petQueryService.countPetByTodoTeamId(todoTeamId);
        return TodoTeamMapper.mapToTodoTeamInfoDetailResponse(todoTeam, registerCount, petCount);
    }

    public TodoTeamNameResponse getTodoTeamLatest() {
        final User requestUser = userUtils.getAccessUser();
        final Register register = registerQueryService.findLatestTodoTeam(requestUser.getId());
        return TodoTeamMapper.mapToTodoTeamNameResponse(register.getTodoTeam(), register);
    }
}
