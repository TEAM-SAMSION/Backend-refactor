package com.pawith.userapplication.service

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import com.pawith.userdomain.User
import com.pawith.userdomain.UserUtils
import com.pawith.userdomain.service.UserReader
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class UserAuthorityGetServiceTest: BehaviorSpec({
    val userReader = mockk<UserReader>()
    val userUtils = mockk<UserUtils>()

    val userAuthorityGetService = UserAuthorityGetService(userReader, userUtils)

    given("사용자의 권한을 조회를 "){
        val userId : Long = FixtureMonkeyUtils.getJavaTypeBasedFixtureMonkey().giveMeOne()
        val user: User = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { userUtils.getIdFromAccessUser() } returns userId
        every { userReader.readByUserId(userId) } returns user
        `when`("정상적으로 요청하면"){
            val response = userAuthorityGetService.getUserAuthority()
            then("요청한 사용자의 권한을 반환한다."){
                response.authority shouldBe user.userAuthority.authority
            }
        }
    }
})