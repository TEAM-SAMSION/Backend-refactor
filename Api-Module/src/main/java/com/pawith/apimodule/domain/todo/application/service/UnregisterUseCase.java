package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.apimodule.domain.todo.application.dto.response.ValidateResponse;
import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.tododomain.entity.Register;
import com.pawith.tododomain.service.RegisterQueryService;
import com.pawith.tododomain.service.RegisterValidateService;
import com.pawith.domain.User;
import com.pawith.domain.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class UnregisterUseCase {
    private final UserUtils userUtils;
    private final RegisterQueryService registerQueryService;
    private final RegisterValidateService registerValidateService;

    public void unregisterTodoTeam(final Long todoTeamId) {
        final User user = userUtils.getAccessUser();
        final Register register = registerQueryService.findRegisterByTodoTeamIdAndUserId(todoTeamId, user.getId());
        register.unregister();
    }

    public ValidateResponse validateRegisterDeletable(final Long todoTeamId) {
        final User user = userUtils.getAccessUser();
        final Register register = registerQueryService.findRegisterByTodoTeamIdAndUserId(todoTeamId, user.getId());
        boolean isNotValidate = registerValidateService.validatePresidentRegisterDeletable(register);
        return new ValidateResponse(isNotValidate);
    }

    public ValidateResponse validateRegistersDeletable() {
        final User user = userUtils.getAccessUser();
        final List<Register> registers = registerQueryService.findAllRegistersByUserId(user.getId());
        boolean isNotValidate = registerValidateService.validateRegisterDeletable(registers);
        return new ValidateResponse(isNotValidate);
    }
}
