package com.pawith.userapplication.service

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import com.pawith.userapplication.dto.request.UserNicknameModifyRequest
import com.pawith.userdomain.UserUtils
import com.pawith.userdomain.service.UserModifier
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UserNicknameChangeServiceTest:BehaviorSpec({
    val userUtils = mockk<UserUtils>()
    val userModifier = mockk<UserModifier>(relaxed=true)

    val userNicknameChangeService = UserNicknameChangeService(userUtils, userModifier)

    given("사용자 닉네임 변경 서비스를 테스트한다") {
        val mockRequest : UserNicknameModifyRequest = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val mockUserId : Long = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { userUtils.getIdFromAccessUser() } returns mockUserId
        `when`("사용자 닉네임을 변경하면") {
            userNicknameChangeService.changeUserNickname(mockRequest)
            then("사용자 닉네임이 변경되고 사용자 권한이 변경된다") {
                verify(exactly=1) {
                    userModifier.modifyNickname(mockUserId, mockRequest.nickname)
                    userModifier.modifyUserAuthorityToUser(mockUserId)
                }
            }
        }
    }
})