package com.pawith.apimodule.domain.user.application.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.response.UserInfoResponse
import com.pawith.userapplication.dto.response.UserJoinTermResponse
import com.pawith.userapplication.mapper.UserMapper
import com.pawith.userdomain.UserUtils
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional(readOnly = true)
class UserInfoGetService (
    private val userUtils: UserUtils,
    private val userMapper: UserMapper
){

    fun getUserInfo(): UserInfoResponse{
        return userMapper.toUserInfoResponse(userUtils.getAccessUser());
    }

    fun getUserJoinTerm():UserJoinTermResponse{
        return UserJoinTermResponse(userUtils.getAccessUser().calculateJoinTerm());
    }
}