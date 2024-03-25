package com.pawith.userdomain.service.authority

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.userdomain.entity.UserAuthority
import com.pawith.userdomain.exception.UserAuthorityNotFoundException
import com.pawith.userdomain.exception.UserError
import com.pawith.userdomain.repository.UserAuthorityRepository

@DomainService
class UserAuthorityReader (
    private val userAuthorityRepository: UserAuthorityRepository
){
    fun readByUserId(userId: Long): UserAuthority {
        return userAuthorityRepository.findByUserId(userId) ?: throw UserAuthorityNotFoundException(UserError.USER_AUTHORITY_NOT_FOUND)
    }
}