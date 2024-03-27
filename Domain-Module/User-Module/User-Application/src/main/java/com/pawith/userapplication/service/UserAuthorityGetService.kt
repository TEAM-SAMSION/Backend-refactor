package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.response.UserAuthorityInfoResponse
import com.pawith.userdomain.UserAuthority
import com.pawith.userdomain.UserUtils
import com.pawith.userdomain.service.UserReader
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional(readOnly = true)
class UserAuthorityGetService (
    private val userReader: UserReader,
    private val userUtils: UserUtils
){

    fun getUserAuthority(): UserAuthorityInfoResponse {
        val userId = userUtils.getIdFromAccessUser()
        val userAuthority : UserAuthority = userReader.readByUserId(userId).userAuthority
        return UserAuthorityInfoResponse(userAuthority.authority)
    }
}