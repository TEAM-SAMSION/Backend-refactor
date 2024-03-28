package com.pawith.userapplication.handler

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.commonmodule.event.UserSignUpEvent
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import com.pawith.userapplication.mapper.UserMapper
import com.pawith.userdomain.User
import com.pawith.userdomain.service.UserAppender
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UserSignUpHandlerTest : BehaviorSpec({
    val userAppender = mockk<UserAppender>(relaxed = true)
    val userMapper = mockk<UserMapper>()
    val userSignUpHandler = UserSignUpHandler(userAppender,  userMapper)



    given("signUp메소드에서") {
        val userSignUpEvent: UserSignUpEvent = FixtureMonkeyUtils.getConstructBasedFixtureMonkey().giveMeOne()
        val user: User = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { userMapper.toUserEntity(userSignUpEvent, any()) } returns user
        `when`("사용자 가입 이벤트가 발생하면"){
            userSignUpHandler.signUp(userSignUpEvent)
            then("사용자를 저장한다."){
                verify(exactly = 1) { userAppender.appendUser(user)  }
            }
        }
    }
})