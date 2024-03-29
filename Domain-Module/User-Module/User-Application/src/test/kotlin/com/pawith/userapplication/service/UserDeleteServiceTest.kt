package com.pawith.userapplication.service

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.commonmodule.event.UserAccountDeleteEvent
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import com.pawith.userdomain.UserUtils
import com.pawith.userdomain.service.UserRemover
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.ApplicationEventPublisher

class UserDeleteServiceTest:BehaviorSpec({
    val userUtils = mockk<UserUtils>()
    val userRemover = mockk<UserRemover>(relaxed=true)
    val applicationEventPublisher = mockk<ApplicationEventPublisher>(relaxed = true)
    val userDeleteService = UserDeleteService(userUtils, userRemover, applicationEventPublisher)

    given("사용자 삭제 서비스를 테스트한다") {
        val userId : Long = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { userUtils.getIdFromAccessUser() } returns userId
        `when`("사용자를 삭제하면") {
            userDeleteService.deleteUser()
            then("사용자가 삭제되고 사용자 삭제 이벤트가 발생한다") {
                verify(exactly=1) {
                    userRemover.removeUser(userId)
                    applicationEventPublisher.publishEvent(any(UserAccountDeleteEvent::class))
                }
            }
        }
    }
})