package com.pawith.userapplication.handler

import com.pawith.commonmodule.event.UserSignUpEvent
import com.pawith.userapplication.mapper.UserMapper
import com.pawith.userdomain.service.authority.UserAuthorityAppender
import com.pawith.userdomain.service.user.UserAppender
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class UserSignUpHandler(
    private val userAppender: UserAppender,
    private val userAuthorityAppender: UserAuthorityAppender,
    private val userMapper: UserMapper
) {

    companion object{
        const val DEFAULT_PROFILE_IMAGE_URL: String = "https://pawith.s3.ap-northeast-2.amazonaws.com/base-image/default_user.png"
    }


    @EventListener
    fun signUp(userSignUpEvent: UserSignUpEvent){
        val user = userMapper.toUserEntity(userSignUpEvent, DEFAULT_PROFILE_IMAGE_URL)
        userAppender.appendUser(user)
        userAuthorityAppender.appendAuthority(user)
    }

}