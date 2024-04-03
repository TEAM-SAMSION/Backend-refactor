package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.apimodule.domain.todo.application.dto.request.AuthorityChangeRequest;
import com.pawith.tododomain.entity.Authority;
import com.pawith.tododomain.entity.Register;
import com.pawith.tododomain.service.RegisterQueryService;
import com.pawith.tododomain.service.RegisterValidateService;
import com.pawith.domain.User;
import com.pawith.domain.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class ChangeRegisterUseCase {

    private final RegisterQueryService registerQueryService;
    private final RegisterValidateService registerValidateService;
    private final UserUtils userUtils;

    public void changeAuthority(Long todoTeamId, Long registerId, AuthorityChangeRequest request) {
        final User user = userUtils.getAccessUser();
        Register userRegister = registerQueryService.findRegisterByTodoTeamIdAndUserId(todoTeamId, user.getId());
        Register register = registerQueryService.findRegisterById(registerId);
        Authority authority = request.getAuthority();
        if (userRegister.isPresident() && authority.equals(Authority.PRESIDENT)) {
            userRegister.updateAuthority(Authority.MEMBER);
        } else {
            registerValidateService.validateAuthorityChangeable(userRegister, authority);
        }

        register.updateAuthority(authority);
    }

    public void changeRegistered(Long registerId) {
        Register register = registerQueryService.findRegisterById(registerId);
        register.unregister();
    }

}
