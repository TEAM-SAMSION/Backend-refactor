package com.pawith.userdomain.service.authority

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.userdomain.entity.User
import com.pawith.userdomain.entity.UserAuthority
import com.pawith.userdomain.repository.UserAuthorityRepository

@DomainService
class UserAuthorityAppender(
    private val userAuthorityRepository: UserAuthorityRepository
) {

    fun appendAuthority(user: User) {
        userAuthorityRepository.findByUserId(user.id)?.initialUserAuthority() ?: {
            userAuthorityRepository.save(UserAuthority.of(user))
        }
    }
}