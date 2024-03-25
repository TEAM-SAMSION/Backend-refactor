package com.pawith.userdomain.utils;

import com.pawith.commonmodule.util.SecurityUtils;
import com.pawith.userdomain.entity.User;
import com.pawith.userdomain.service.user.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserUtils {

    private final UserReader userReader;

    public User getAccessUser(){
        final Long userId = SecurityUtils.getAuthenticationPrincipal();
        return userReader.findById(userId);
    }

    public Long getIdFromAccessUser(){
        return SecurityUtils.getAuthenticationPrincipal();
    }
}
