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
    fun findByEmail(email: String): User {
        return findUser { userRepository.findByEmail(email) }
    }

    fun findById(userId: Long): User {
        return findUser { userRepository.findByIdOrNull(userId) }
    }

    fun findUserMapByIds(userIds: List<Long>) : Map<Long, User> {
        return userRepository.findAllByIds(userIds).associateBy { it.id }
    }

    fun findAllByNicknameAndUserIds(nickname: String, userIds: List<Long>) : List<User>{
        return userRepository.findAllByNicknameAndIds(nickname, userIds)
    }

    private fun findUser(findMethod: () -> User?): User {
        return findMethod() ?: throw UserNotFoundException(UserError.USER_NOT_FOUND)
    }
}