package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.response.UserAuthorityInfoResponse
import com.pawith.userdomain.service.UserAuthorityQueryService
import com.pawith.userdomain.utils.UserUtils
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional
class UserAuthorityGetService (
    val userAuthorityQueryService: UserAuthorityQueryService,
    val userUtils: UserUtils
){

    fun readUserAuthority(): UserAuthorityInfoResponse {
        val userId = userUtils.idFromAccessUser;
        val userAuthority = userAuthorityQueryService.findByUserId(userId);
        return UserAuthorityInfoResponse(userAuthority.authority);
    }
}