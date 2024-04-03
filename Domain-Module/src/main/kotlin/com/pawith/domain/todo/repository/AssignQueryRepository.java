package com.pawith.domain.todo.repository;

import com.pawith.domain.todo.entity.Assign;
import com.pawith.domain.todo.entity.CompletionStatus;
import com.pawith.domain.todo.repository.dao.IncompleteAssignInfoDao;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AssignQueryRepository {
    List<Assign> findAllByCategoryIdAndScheduledDateQuery(Long categoryId, LocalDate scheduledDate);
    List<Assign> findAllByUserIdAndTodoTeamIdAndScheduledDateQuery(Long userId, Long todoTeamId, LocalDate scheduledDate);
    void deleteByRegisterIdsQuery(final List<Long> registerIds);
    void deleteAllByTodoIdQuery(final Long todoId);
    Optional<Assign> findByTodoIdAndUserIdQuery(Long todoId, Long userId);
    List<Assign> findAllByTodoIdQuery(Long todoId);
    List<Assign> findAllByTodoIdWithRegisterFetchQuery(Long todoId);
    void deleteAllByCategoryIdQuery(final Long categoryId);

    Long countByTodoIdAndCompletedQuery(Long todoId, @Nullable CompletionStatus completionStatus);

    <T extends IncompleteAssignInfoDao> List<T> findAllAssignInfoByTodoIdAndCompleteStatusQuery(Long todoId, CompletionStatus completionStatus);
}
