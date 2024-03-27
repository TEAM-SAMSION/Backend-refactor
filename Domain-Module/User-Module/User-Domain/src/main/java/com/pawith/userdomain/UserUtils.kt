package com.pawith.userdomain

import com.pawith.commonmodule.util.SecurityUtils
import com.pawith.userdomain.service.UserReader
import org.springframework.stereotype.Component

@Component
class UserUtils(
    val userReader: UserReader
) {
    fun getAccessUser(): User{
        val userId = SecurityUtils.getAuthenticationPrincipal()
        return userReader.readByUserId(userId)
    }

    fun getIdFromAccessUser() : Long{
        return SecurityUtils.getAuthenticationPrincipal()
    }
}