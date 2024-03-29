package com.pawith.userdomain

import com.pawith.commonmodule.domain.BaseDomain
import com.pawith.commonmodule.enums.Provider
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class User(
    val id: Long?,
    var nickname: String,
    var email: String,
    var imageUrl: String,
    var provider: Provider?,
    var userAuthority: UserAuthority,
    var isDeleted: Boolean = false,
    createdDate: LocalDateTime,
    updatedDate: LocalDateTime
) : BaseDomain(createdDate, updatedDate) {

    companion object {
        fun createNewUser(
            nickname: String,
            email: String,
            imageUrl: String,
            userAuthority: UserAuthority
        ) = User(
            id=null,
            nickname = nickname,
            email = email,
            imageUrl = imageUrl,
            provider = null,
            userAuthority = userAuthority,
            createdDate = LocalDateTime.now(),
            updatedDate = LocalDateTime.now()
        )
    }

    fun updateEmail(newEmail: String) {
        this.email = newEmail
    }

    fun changeNickname(newNickname: String) {
        this.nickname = newNickname
    }

    fun changeProfileImage(newImageUrl: String) {
        this.imageUrl = newImageUrl
    }

    fun calculateJoinTerm(): Long {
        return ChronoUnit.DAYS.between(this.createdDate.toLocalDate(), LocalDateTime.now()) + 1;
    }

    fun isProviderMatching(provider: Provider): Boolean {
        return this.provider == provider
    }

    fun changeUserAuthorityGuestToUser() {
        if (userAuthority.isGuest()) {
            this.userAuthority = UserAuthority.createUserAuthority()
            updateModifiedDateToCurrent()
        }
    }
}

