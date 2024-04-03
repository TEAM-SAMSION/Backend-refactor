package com.pawith.domain.user.service

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.domain.user.User
import com.pawith.domain.user.exception.UserError
import com.pawith.domain.user.exception.UserNotFoundException
import com.pawith.domain.user.repository.UserRepository

@DomainService
class UserReader(
    private val userRepository: UserRepository
) {
    fun readByEmail(email: String): User {
        return readUser { userRepository.findByEmailOrNull(email) }
    }

    fun readByUserId(userId: Long): User {
        return readUser { userRepository.findByIdOrNull(userId) }
    }

    fun readUsersMapByIds(userIds: List<Long>) : Map<Long, User> {
        return userRepository.findAllByIds(userIds).associateBy { it.id!! }
    }

    fun readAllByNicknameAndUserIds(nickname: String, userIds: List<Long>) : List<User>{
        return userRepository.findAllByNicknameAndIds(nickname, userIds)
    }

    private fun readUser(findMethod: () -> User?): User {
        return findMethod() ?: throw UserNotFoundException(UserError.USER_NOT_FOUND)
    }
}