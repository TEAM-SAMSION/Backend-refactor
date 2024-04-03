package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.tododomain.entity.Assign;
import com.pawith.tododomain.service.AssignQueryService;
import com.pawith.tododomain.service.TodoNotificationSaveService;
import com.pawith.domain.User;
import com.pawith.domain.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class TodoNotificationCreateUseCase {

    private final TodoNotificationSaveService todoNotificationSaveService;
    private final AssignQueryService assignQueryService;
    private final UserUtils userUtils;

    public void createNotification(Long todoId, LocalTime notificationTime) {
        final User user = userUtils.getAccessUser();
        final Assign assign = assignQueryService.findAssignByTodoIdAndUserId(todoId, user.getId());
        todoNotificationSaveService.saveNotification(notificationTime, assign);
    }
}
