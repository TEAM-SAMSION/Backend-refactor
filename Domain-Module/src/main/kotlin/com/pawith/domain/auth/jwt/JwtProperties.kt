package com.pawith.domain.auth.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix="jwt")
class JwtProperties(
    val secret: String,
    val accessTokenExpirationTime: Long,
    val refreshTokenExpirationTime: Long,
) {
}