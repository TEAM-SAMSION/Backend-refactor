package com.pawith.userdomain.service.user

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.userdomain.entity.User
import com.pawith.userdomain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull

@DomainService
class UserModifier(
    private val userRepository: UserRepository
) {
    fun modifyNickname(userId: Long, newNickname: String){
        userRepository.findByIdOrNull(userId)?.let {
            it.updateNickname(newNickname)
            userRepository.save(it)
        }
    }

    fun modifyProfileImage(userId: Long, newProfileImage: String){
        userRepository.findByIdOrNull(userId)?.let {
            it.updateProfileImage(newProfileImage)
            userRepository.save(it)
        }
    }
}