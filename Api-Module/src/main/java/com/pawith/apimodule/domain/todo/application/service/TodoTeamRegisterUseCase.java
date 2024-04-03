package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.tododomain.entity.TodoTeam;
import com.pawith.tododomain.service.RegisterSaveService;
import com.pawith.tododomain.service.TodoTeamQueryService;
import com.pawith.domain.User;
import com.pawith.domain.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class TodoTeamRegisterUseCase {
    private final UserUtils userUtils;
    private final TodoTeamQueryService todoTeamQueryService;
    private final RegisterSaveService registerSaveService;

    public void registerTodoTeam(String todoTeamCode) {
        final User user = userUtils.getAccessUser();
        final TodoTeam todoTeam = todoTeamQueryService.findTodoTeamByCode(todoTeamCode);
        registerSaveService.saveRegisterAboutMember(todoTeam, user.getId());
    }
}
