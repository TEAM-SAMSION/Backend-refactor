package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.response.UserInfoResponse
import com.pawith.userapplication.dto.response.UserJoinTermResponse
import com.pawith.userapplication.mapper.UserMapper
import com.pawith.userdomain.utils.UserUtils
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional
class UserInfoGetService (
    val userUtils: UserUtils,
    val userMapper: UserMapper
){

    fun readUserInfo(): UserInfoResponse{
        return userMapper.toUserInfoResponse(userUtils.accessUser);
    }

    fun readUserJoinTerm():UserJoinTermResponse{
        return UserJoinTermResponse(userUtils.accessUser.joinTerm);
    }
}