package com.pawith.userapplication.handler

import com.pawith.commonmodule.event.UserSignUpEvent
import com.pawith.userapplication.mapper.UserMapper
import com.pawith.userdomain.service.UserAuthoritySaveService
import com.pawith.userdomain.service.UserSaveService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserSignUpHandler(
    val userSaveService: UserSaveService,
    val userAuthoritySaveService: UserAuthoritySaveService,
    val userMapper: UserMapper
) {

    companion object{
        const val DEFAULT_PROFILE_IMAGE_URL: String = "https://pawith.s3.ap-northeast-2.amazonaws.com/base-image/default_user.png"
    }


    @EventListener
    fun signUp(userSignUpEvent: UserSignUpEvent){
        val user = userMapper.toUserEntity(userSignUpEvent, DEFAULT_PROFILE_IMAGE_URL)
        userSaveService.saveUser(user)
        userAuthoritySaveService.saveUserAuthority(user)
    }

}