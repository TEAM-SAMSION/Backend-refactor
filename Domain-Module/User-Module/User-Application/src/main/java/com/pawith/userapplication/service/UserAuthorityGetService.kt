package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.response.UserAuthorityInfoResponse
import com.pawith.userdomain.service.authority.UserAuthorityReader
import com.pawith.userdomain.utils.UserUtils
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional(readOnly = true)
class UserAuthorityGetService (
    private val userAuthorityReader: UserAuthorityReader,
    private val userUtils: UserUtils
){

    fun getUserAuthority(): UserAuthorityInfoResponse {
        val userId = userUtils.idFromAccessUser;
        val userAuthority = userAuthorityReader.readByUserId(userId);
        return UserAuthorityInfoResponse(userAuthority.authority);
    }
}