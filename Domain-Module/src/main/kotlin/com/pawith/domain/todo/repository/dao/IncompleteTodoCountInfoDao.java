package com.pawith.domain.todo.repository.dao;

public interface IncompleteTodoCountInfoDao {
    Long getTodoTeamId();
    Long getUserId();
    String getTodoTeamName();
    Long getIncompleteTodoCount();
}
