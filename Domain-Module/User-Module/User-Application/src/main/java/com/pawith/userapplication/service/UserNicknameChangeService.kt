package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.request.UserNicknameModifyRequest
import com.pawith.userdomain.service.authority.UserAuthorityModifier
import com.pawith.userdomain.service.authority.UserAuthorityReader
import com.pawith.userdomain.service.user.UserModifier
import com.pawith.userdomain.utils.UserUtils
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional
class UserNicknameChangeService (
    private val userUtils: UserUtils,
    private val userModifier: UserModifier,
    private val userAuthorityModifier: UserAuthorityModifier
){

    fun changeUserNickname(request: UserNicknameModifyRequest){
        val userId = userUtils.idFromAccessUser
        userModifier.modifyNickname(userId, request.nickname)
        userAuthorityModifier.modifyAuthorityGuestToUser(userId)
    }
}