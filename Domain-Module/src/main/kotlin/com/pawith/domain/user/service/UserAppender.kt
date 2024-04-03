package com.pawith.domain.user.service

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.userdomain.User
import com.pawith.userdomain.repository.UserRepository

@DomainService
class UserAppender(
    private val userRepository: UserRepository
) {
    fun appendUser(user: User){
        userRepository.save(user)
    }
}