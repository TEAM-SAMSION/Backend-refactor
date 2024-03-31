package com.pawith.userapplication.service

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import com.pawith.userapplication.dto.response.UserInfoResponse
import com.pawith.userapplication.dto.response.UserJoinTermResponse
import com.pawith.userapplication.mapper.UserMapper
import com.pawith.userdomain.User
import com.pawith.userdomain.UserUtils
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class UserInfoGetServiceTest : BehaviorSpec({
    val userUtils = mockk<UserUtils>()
    val userMapper = mockk<UserMapper>()
    val userInfoGetService = UserInfoGetService(userUtils, userMapper)

    given("유저 정보 조회 서비스를 테스트한다") {
        val user: User = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val userInfoResponse : UserInfoResponse = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { userUtils.getAccessUser() } returns user
        every { userMapper.toUserInfoResponse(user) } returns userInfoResponse
        `when`("유저 정보를 조회하면") {
            val userInfoResponseResult = userInfoGetService.getUserInfo()
            then("유저 정보가 조회된다") {
                userInfoResponseResult shouldBe userInfoResponse
            }
        }

        `when`("유저 가입 기간을 조회하면") {
            val userJoinTermResponse = userInfoGetService.getUserJoinTerm()
            then("유저 가입 기간이 조회된다") {
                userJoinTermResponse shouldBe UserJoinTermResponse(user.calculateJoinTerm())
            }
        }
    }
})