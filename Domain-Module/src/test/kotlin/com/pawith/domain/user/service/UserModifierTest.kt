package com.pawith.domain.user.service

import com.pawith.domain.user.repository.UserRepository
import com.pawith.domain.user.service.UserModifier
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify

class UserModifierTest:BehaviorSpec({
    val userRepository = mockk<UserRepository>()
    val userModifier = UserModifier(userRepository)

    given("UserModifier의 modifyNickname 메소드를 테스트한다") {
        `when`("User의 닉네임을 변경하면") {
            userModifier.modifyNickname(1, "newNickname")
            then("UserRepository의 save 메소드가 호출된다") {
                 verify(exactly = 1) { userRepository.save(any()) }
            }
        }
    }
})