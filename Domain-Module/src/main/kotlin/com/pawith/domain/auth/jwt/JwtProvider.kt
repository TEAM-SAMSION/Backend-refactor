package com.pawith.domain.auth.jwt

import com.pawith.domain.auth.jwt.PrivateClaims.UserClaims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider( // 토큰을 캐싱하는 역할은 따로 제공할 예정
    private val jwtProperties: JwtProperties,
    private val jwtKeyFactory: JwtKeyFactory,
    private val jwtValidator: JwtValidator
) {
    fun generateAccessToken(userClaims: UserClaims): String {
        return generateBasicToken(
            userClaims.createPrivateClaims(TokenType.ACCESS_TOKEN),
            jwtProperties.accessTokenExpirationTime
        )
    }

    fun generateRefreshToken(userClaims: UserClaims) : String {
        return generateBasicToken(
            userClaims.createPrivateClaims(TokenType.REFRESH_TOKEN),
            jwtProperties.refreshTokenExpirationTime
        )
    }

    fun generateToken(userClaims: UserClaims): Token {
        return Token(
            accessToken = generateAccessToken(userClaims),
            refreshToken = generateRefreshToken(userClaims)
        )
    }

    fun reissueToken(refreshToken: String) : Token {
        jwtValidator.validateToken(refreshToken, TokenType.REFRESH_TOKEN)
        val userClaims = extractUserClaimsFromToken(refreshToken, TokenType.REFRESH_TOKEN)
        val generatedAccessToken = generateAccessToken(userClaims)
        val generatedRefreshToken = generateRefreshToken(userClaims)

        return Token(
            accessToken=generatedAccessToken,
            refreshToken=generatedRefreshToken
        )
    }

    fun extractUserClaimsFromToken(token: String, tokenType: TokenType): UserClaims {
        return jwtValidator.initializeJwtParser(tokenType)
            .parseSignedClaims(token)
            .payload
            .get(JwtConsts.USER_CLAIMS, UserClaims::class.java)
    }

    private fun generateBasicToken(privateClaims: PrivateClaims, expireTime: Long): String {
        val now = Date()
        return Jwts.builder()
            .issuer(JwtConsts.TOKEN_ISSUER)
            .claims(privateClaims.convertToClaims())
            .issuedAt(now)
            .expiration(Date(now.time + expireTime))
            .signWith(jwtKeyFactory.generateKey())
            .compact()
    }

    data class Token(
        val accessToken: String,
        val refreshToken: String
    )
}