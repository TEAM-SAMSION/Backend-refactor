package com.pawith.userdomain.service

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.userdomain.repository.UserRepository

@DomainService
class UserRemover(
    private val userRepository: UserRepository
) {
    fun removeUser(userId: Long){
        userRepository.deleteById(userId)
    }

}