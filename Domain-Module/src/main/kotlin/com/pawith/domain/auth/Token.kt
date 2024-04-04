package com.pawith.domain.auth

import com.pawith.domain.auth.jwt.TokenType

class Token(
    private val id: Long?,
    private val value: String,
    private val tokenType: TokenType,
    private val userId: Long
) {
}