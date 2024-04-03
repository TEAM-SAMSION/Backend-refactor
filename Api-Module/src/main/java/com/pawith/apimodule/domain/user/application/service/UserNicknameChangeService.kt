package com.pawith.apimodule.domain.user.application.service

import com.pawith.apimodule.domain.user.application.dto.request.UserNicknameModifyRequest
import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.request.UserNicknameModifyRequest
import com.pawith.userdomain.UserUtils
import com.pawith.userdomain.service.UserModifier
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional
class UserNicknameChangeService (
    private val userUtils: UserUtils,
    private val userModifier: UserModifier
){

    fun changeUserNickname(request: UserNicknameModifyRequest){
        val userId = userUtils.getIdFromAccessUser()
        userModifier.modifyNickname(userId, request.nickname)
        userModifier.modifyUserAuthorityToUser(userId)
    }
}