package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.request.UserNicknameModifyRequest
import com.pawith.userdomain.service.UserAuthorityQueryService
import com.pawith.userdomain.utils.UserUtils
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional
class UserNicknameChangeService (
    val userUtils: UserUtils,
    val userAuthorityQueryService: UserAuthorityQueryService
){

    fun changeUserNickname(request: UserNicknameModifyRequest){
        val user = userUtils.accessUser
        user.updateNickname(request.nickname)
        val userAuthority = userAuthorityQueryService.findByUserId(user.id)
        userAuthority.changeUserAuthority()
    }
}