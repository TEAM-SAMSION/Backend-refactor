package com.pawith.userapplication.mapper

import com.pawith.commonmodule.annotation.Mapper
import com.pawith.commonmodule.event.UserSignUpEvent
import com.pawith.userapplication.dto.response.UserInfoResponse
import com.pawith.userdomain.entity.User

@Mapper
class UserMapper {

    fun toUserEntity(event: UserSignUpEvent, imageUrl: String): User {
        return User(
            event.nickname,
            event.email,
            imageUrl,
            null
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