package com.pawith.userdomain.service

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.userdomain.repository.UserRepository

@DomainService
class UserModifier(
    private val userRepository: UserRepository
) {
    fun modifyNickname(userId: Long, newNickname: String){
        userRepository.findByIdOrNull(userId)?.let {
            it.changeNickname(newNickname)
            userRepository.update(it)
        }
    }

    fun modifyProfileImage(userId: Long, newProfileImage: String){
        userRepository.findByIdOrNull(userId)?.let {
            it.changeProfileImage(newProfileImage)
            userRepository.update(it)
        }
    }

    fun modifyUserAuthorityToUser(userId: Long){
        userRepository.findByIdOrNull(userId)?.let {
            it.changeUserAuthorityGuestToUser()
            userRepository.update(it)
        }
    }
}