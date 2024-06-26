package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.apimodule.domain.todo.application.handler.event.TodoDeleteEvent;
import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.tododomain.entity.Register;
import com.pawith.tododomain.entity.Todo;
import com.pawith.tododomain.service.*;
import com.pawith.domain.User;
import com.pawith.domain.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class TodoDeleteUseCase {

    private final TodoDeleteService todoDeleteService;
    private final TodoQueryService todoQueryService;
    private final TodoTeamQueryService todoTeamQueryService;
    private final RegisterQueryService registerQueryService;
    private final TodoValidateService todoValidateService;
    private final UserUtils userUtils;
    private final ApplicationEventPublisher applicationEventPublisher;


    public void deleteTodoByTodoId(Long todoId) {
        User accessUser = userUtils.getAccessUser();
        final Todo todo = todoQueryService.findTodoByTodoId(todoId);
        final Long todoTeamId = todoTeamQueryService.findTodoTeamByTodoId(todoId).getId();
        final Register register = registerQueryService.findRegisterByTodoTeamIdAndUserId(todoTeamId, accessUser.getId());
        todoValidateService.validateTodoDeletable(todo, register);
        todoDeleteService.deleteTodoByTodoId(todoId);
        applicationEventPublisher.publishEvent(new TodoDeleteEvent(todoId));
    }


}
