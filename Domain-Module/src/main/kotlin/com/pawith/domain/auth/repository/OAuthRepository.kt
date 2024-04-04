package com.pawith.domain.auth.repository

import com.pawith.domain.auth.OAuth

interface OAuthRepository {
    fun existsBySub(sub: String)

    fun existsByUserId(userId: Long)

    fun findBySubOrNull(sub: String) : OAuth?

    fun findByUserIdOrNull(userId: Long) : OAuth?
}