package com.pawith.domain.auth

import com.pawith.commonmodule.enums.Provider

class OAuth(
    private val id: Long?,
    private val provider: Provider,
    private val sub: String,
    private val userId: Long
) {
}