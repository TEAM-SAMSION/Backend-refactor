package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.apimodule.domain.todo.application.dto.response.RegisterTermResponse;
import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.commonmodule.response.ListResponse;
import com.pawith.apimodule.domain.todo.application.dto.response.RegisterInfoResponse;
import com.pawith.apimodule.domain.todo.application.dto.response.RegisterManageInfoResponse;
import com.pawith.apimodule.domain.todo.application.dto.response.RegisterSearchInfoResponse;
import com.pawith.tododomain.entity.Register;
import com.pawith.tododomain.service.RegisterQueryService;
import com.pawith.tododomain.util.RegisterUtils;
import com.pawith.domain.User;
import com.pawith.domain.UserUtils;
import com.pawith.domain.service.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegistersGetUseCase {

    private final UserUtils userUtils;
    private final RegisterQueryService registerQueryService;
    private final UserReader userReader;

    public ListResponse<RegisterInfoResponse> getRegisters(final Long teamId) {
        final List<Register> allRegisters = registerQueryService.findAllRegistersByTodoTeamId(teamId);
        final Map<Long, User> registerUserMap = userReader.readUsersMapByIds(RegisterUtils.extractUserIds(allRegisters));
        final List<RegisterInfoResponse> registerSimpleInfoResponses = allRegisters.stream()
            .map(register -> {
                final User registerUser = registerUserMap.get(register.getUserId());
                return new RegisterInfoResponse(register.getId(), registerUser.getNickname());
            })
            .toList();
        return ListResponse.from(registerSimpleInfoResponses);
    }

    public ListResponse<RegisterManageInfoResponse> getManageRegisters(final Long teamId) {
        final List<Register> allRegisters = RegisterUtils.sortByAuthority(registerQueryService.findAllRegistersByTodoTeamId(teamId));
        final Map<Long, User> registerUserMap = userReader.readUsersMapByIds(RegisterUtils.extractUserIds(allRegisters));
        final List<RegisterManageInfoResponse> manageRegisterInfoResponses =
            allRegisters.stream()
                .map(register -> {
                    final User registerUser = registerUserMap.get(register.getUserId());
                    return new RegisterManageInfoResponse(register.getId(), register.getAuthority(), registerUser.getNickname(), registerUser.getEmail(), registerUser.getImageUrl());
                })
                .toList();
        return ListResponse.from(manageRegisterInfoResponses);
    }

    public RegisterTermResponse getRegisterTerm(final Long teamId) {
        final User user = userUtils.getAccessUser();
        final Integer registerTerm = registerQueryService.findUserRegisterTerm(teamId, user.getId());
        return new RegisterTermResponse(registerTerm);
    }

    public ListResponse<RegisterSearchInfoResponse> searchRegisterByNickname(final Long todoTeamId, final String nickname) {
        final List<Register> registers = RegisterUtils.sortByAuthority(registerQueryService.findAllRegistersByTodoTeamId(todoTeamId));
        final List<User> users = userReader.readAllByNicknameAndUserIds(nickname, RegisterUtils.extractUserIds(registers));
        final Map<Long, Register> registerMap = RegisterUtils.convertToMapWithUserIdKey(registers);
        final List<RegisterSearchInfoResponse> registerSearchInfoResponses = users.stream()
            .map(user -> {
                final Register register = registerMap.get(user.getId());
                return new RegisterSearchInfoResponse(register.getId(), register.getAuthority(), user.getNickname(), user.getEmail(), user.getImageUrl());
            })
            .toList();
        return ListResponse.from(registerSearchInfoResponses);
    }
}
