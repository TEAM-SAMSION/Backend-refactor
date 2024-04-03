package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.apimodule.domain.todo.application.dto.response.TodoProgressRateCompareResponse;
import com.pawith.apimodule.domain.todo.application.dto.response.TodoProgressResponse;
import com.pawith.tododomain.service.TodoQueryService;
import com.pawith.domain.User;
import com.pawith.domain.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoRateGetUseCase {

    private final UserUtils userUtils;
    private final TodoQueryService todoQueryService;

    /**
     * 리팩터링 전, 100명 동시 요청 평균 : 426ms
     * <br>리팩터링 후, 100명 동시 요청 평균 : 67ms(535% 성능개선)
     */
    public TodoProgressResponse getTodoProgress(final Long todoTeamId) {
        final User user = userUtils.getAccessUser();
        final Integer todoCompleteRate = todoQueryService.findTodoCompleteRate(user.getId(), todoTeamId);
        return new TodoProgressResponse(todoCompleteRate);
    }

    public TodoProgressRateCompareResponse getWeekProgressCompare(final Long todoTeamId) {
        final User user = userUtils.getAccessUser();
        final Integer thisWeekTodoCompleteRate = todoQueryService.findThisWeekTodoCompleteRate(user.getId(), todoTeamId);
        final Integer lastWeekTodoCompleteRate = todoQueryService.findLastWeekTodoCompleteRate(user.getId(), todoTeamId);
        return new TodoProgressRateCompareResponse(thisWeekTodoCompleteRate > lastWeekTodoCompleteRate, thisWeekTodoCompleteRate.equals(lastWeekTodoCompleteRate));
    }
}
