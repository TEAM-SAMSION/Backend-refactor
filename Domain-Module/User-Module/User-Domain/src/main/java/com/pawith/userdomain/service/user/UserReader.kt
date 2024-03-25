package com.pawith.userdomain.service.user

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.userdomain.entity.User
import com.pawith.userdomain.exception.UserError
import com.pawith.userdomain.exception.UserNotFoundException
import com.pawith.userdomain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull

@DomainService
class UserReader(
    private val userRepository: UserRepository
) {
    fun readByEmail(email: String): User {
        return readUser { userRepository.findByEmail(email) }
    }

    fun readByUserId(userId: Long): User {
        return readUser { userRepository.findByIdOrNull(userId) }
    }

    fun readUsersMapByIds(userIds: List<Long>) : Map<Long, User> {
        return userRepository.findAllByIds(userIds).associateBy { it.id }
    }

    fun readAllByNicknameAndUserIds(nickname: String, userIds: List<Long>) : List<User>{
        return userRepository.findAllByNicknameAndIds(nickname, userIds)
    }

    private fun readUser(findMethod: () -> User?): User {
        return findMethod() ?: throw UserNotFoundException(UserError.USER_NOT_FOUND)
    }
}