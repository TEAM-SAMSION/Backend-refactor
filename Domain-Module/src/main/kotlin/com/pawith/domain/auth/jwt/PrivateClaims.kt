package com.pawith.domain.auth.jwt

import com.fasterxml.jackson.annotation.JsonProperty

class PrivateClaims(
    private val userClaims: PrivateClaims.UserClaims,
    private val tokenType: TokenType
) {

    companion object{
        fun retrieveClaimsClassType(): Map<String, Class<*>> = mapOf(
            JwtConsts.USER_CLAIMS to UserClaims::class.java,
            JwtConsts.TOKEN_TYPE to TokenType::class.java
        )
    }
    fun convertToClaims(): Map<String, Any> = mapOf(
        JwtConsts.USER_CLAIMS to userClaims,
        JwtConsts.TOKEN_TYPE to tokenType
    )



    class UserClaims(
        @JsonProperty("user_id") private val userId: Long
    ) {
        fun createPrivateClaims(tokenType: TokenType) = PrivateClaims(this, tokenType)
    }
}