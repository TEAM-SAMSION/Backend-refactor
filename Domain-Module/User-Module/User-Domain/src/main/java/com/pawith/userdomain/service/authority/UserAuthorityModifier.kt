package com.pawith.userdomain.service.authority

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.userdomain.entity.UserAuthority
import com.pawith.userdomain.repository.UserAuthorityRepository

@DomainService
class UserAuthorityModifier(
    private val userAuthorityRepository: UserAuthorityRepository
) {

    fun modifyAuthorityGuestToUser(userId: Long){
        userAuthorityRepository.findByUserId(userId)?.let{
            it.changeUserAuthority()
            userAuthorityRepository.save(it)
        }
    }
}