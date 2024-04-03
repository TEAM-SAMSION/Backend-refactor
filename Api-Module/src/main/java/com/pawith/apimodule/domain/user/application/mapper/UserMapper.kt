package com.pawith.apimodule.domain.user.application.mapper

import com.pawith.commonmodule.annotation.Mapper
import com.pawith.commonmodule.event.UserSignUpEvent
import com.pawith.userapplication.dto.response.UserInfoResponse
import com.pawith.userdomain.User
import com.pawith.userdomain.UserAuthority

@Mapper
class UserMapper {

    fun toUserEntity(event: UserSignUpEvent, imageUrl: String): User {
        return User.createNewUser(
            nickname = event.nickname,
            email = event.email,
            imageUrl=imageUrl,
            userAuthority = UserAuthority.createGuestAuthority()
        )
    }

    fun toUserInfoResponse(user: User): UserInfoResponse {
        return UserInfoResponse(
            user.nickname,
            user.email,
            user.imageUrl
        )
    }
}