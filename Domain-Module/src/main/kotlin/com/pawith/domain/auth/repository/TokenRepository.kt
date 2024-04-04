package com.pawith.domain.auth.repository

import com.pawith.domain.auth.Token
import com.pawith.domain.auth.jwt.TokenType

interface TokenRepository {

    fun findByValueAndTokenTypeOrNull(value: String, tokenType: TokenType) : Token?

    fun deleteByValue(value: String)

    fun existsByValueAndTokenType(value: String, tokenType: TokenType) : Boolean


}