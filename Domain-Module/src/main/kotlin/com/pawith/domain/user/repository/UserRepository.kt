package com.pawith.domain.user.repository

import com.pawith.domain.user.User
interface UserRepository {
    fun findByEmailOrNull(email: String): User?
    fun findByIdOrNull(id: Long): User?
    fun existsByEmail(email: String): Boolean

    fun findAllByIds(ids: List<Long>): List<User>

    fun findAllByNicknameAndIds(nickname: String, ids: List<Long>): List<User>

    fun save(user: User) : User

    fun update(user: User) : User

    fun deleteById(id: Long)
}