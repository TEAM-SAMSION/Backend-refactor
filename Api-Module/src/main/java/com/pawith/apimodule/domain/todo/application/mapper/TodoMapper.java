package com.pawith.apimodule.domain.todo.application.mapper;

import com.pawith.apimodule.domain.todo.application.dto.response.TodoNotificationInfoResponse;
import com.pawith.apimodule.domain.todo.application.dto.request.TodoCreateRequest;
import com.pawith.apimodule.domain.todo.application.dto.response.AssignUserInfoResponse;
import com.pawith.apimodule.domain.todo.application.dto.response.CategorySubTodoResponse;
import com.pawith.tododomain.entity.Category;
import com.pawith.tododomain.entity.Todo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoMapper {

    public static Todo mapToTodo(TodoCreateRequest request, Category category, Long registerId){
        return Todo.builder()
            .category(category)
            .description(request.getDescription())
            .scheduledDate(request.getScheduledDate())
            .creatorId(registerId)
            .build();
    }

    public static CategorySubTodoResponse mapToCategorySubTodoResponse(Todo todo,
                                                                       List<AssignUserInfoResponse> assignsList,
                                                                       Boolean isAssigned,
                                                                       TodoNotificationInfoResponse todoNotificationInfoResponse){
        return CategorySubTodoResponse.builder()
            .todoId(todo.getId())
            .task(todo.getDescription())
            .completionStatus(todo.getCompletionStatus())
            .assignNames(assignsList)
            .isAssigned(isAssigned)
            .notificationInfo(todoNotificationInfoResponse)
            .build();
    }
}
