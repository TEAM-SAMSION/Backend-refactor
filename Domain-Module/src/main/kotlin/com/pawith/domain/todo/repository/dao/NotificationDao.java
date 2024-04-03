package com.pawith.domain.todo.repository.dao;

import java.time.LocalDate;
import java.time.LocalTime;

public interface NotificationDao {
    Long getTodoTeamId();
    Long getUserId();
    String getCategoryName();
    String getTodoDescription();
    LocalTime getNotificationTime();
    LocalDate getScheduledDate();

    String getTodoTeamName();
}
