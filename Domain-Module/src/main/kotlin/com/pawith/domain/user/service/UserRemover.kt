package com.pawith.domain.user.service

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.domain.user.repository.UserRepository
@DomainService
class UserRemover(
    private val userRepository: UserRepository
) {
    fun removeUser(userId: Long){
        userRepository.deleteById(userId)
    }

}