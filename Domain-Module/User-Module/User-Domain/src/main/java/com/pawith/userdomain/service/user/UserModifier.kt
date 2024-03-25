package com.pawith.userdomain.service.user

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.userdomain.entity.User
import com.pawith.userdomain.repository.UserRepository

@DomainService
class UserModifier(
    private val userRepository: UserRepository
) {
    fun modifyNickname(user: User, newNickname: String){
        user.updateNickname(newNickname);
        userRepository.save(user);
    }
}