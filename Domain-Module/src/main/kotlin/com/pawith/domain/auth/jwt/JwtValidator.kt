package com.pawith.domain.auth.jwt

import com.pawith.domain.auth.exception.AuthError
import com.pawith.domain.auth.jwt.exception.ExpiredTokenException
import com.pawith.domain.auth.jwt.exception.InvalidTokenException
import io.jsonwebtoken.*
import io.jsonwebtoken.jackson.io.JacksonDeserializer
import io.jsonwebtoken.security.SignatureException
import org.springframework.stereotype.Component

@Component
class JwtValidator(
    private val jwtKeyFactory: JwtKeyFactory
) {

    fun validateToken(token: String, tokenType: TokenType) {
        val jwtParser = initializeJwtParser(tokenType)
        try {
            jwtParser.parse(token)
        } catch (ex: Exception) {
            when (ex) {
                is MalformedJwtException,
                is SignatureException,
                is IncorrectClaimException,
                is IllegalArgumentException -> throw InvalidTokenException(AuthError.INVALID_TOKEN)

                is ExpiredJwtException -> throw ExpiredTokenException(AuthError.EXPIRED_TOKEN)
                else -> throw ex
            }
        }
    }


    fun initializeJwtParser(tokenType: TokenType): JwtParser {
        return Jwts.parser()
            .json(JacksonDeserializer(PrivateClaims.retrieveClaimsClassType()))
            .verifyWith(jwtKeyFactory.generateKey())
            .requireIssuer(JwtConsts.TOKEN_ISSUER)
            .require(JwtConsts.TOKEN_TYPE, tokenType)
            .build()
    }
}