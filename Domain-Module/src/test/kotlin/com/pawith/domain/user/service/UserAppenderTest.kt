package com.pawith.domain.user.service

import com.pawith.domain.user.repository.UserRepository
import com.pawith.domain.user.service.UserAppender
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify

class UserAppenderTest:BehaviorSpec({
    val userRepository = mockk<UserRepository>()

    val userAppender = UserAppender(userRepository)

    given("UserAppender의 appendUser 메소드를 테스트한다") {
        `when`("User를 저장하면") {
            userAppender.appendUser(mockk())
            then("UserRepository의 save 메소드가 호출된다") {
                 verify(exactly = 1) { userRepository.save(any()) }
            }
        }
    }
})