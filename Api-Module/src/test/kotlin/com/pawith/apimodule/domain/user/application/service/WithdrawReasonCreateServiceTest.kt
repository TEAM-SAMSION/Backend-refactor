package com.pawith.apimodule.domain.user.application.service

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.apimodule.domain.user.application.dto.request.WithdrawReasonCreateRequest
import com.pawith.apimodule.domain.user.application.mapper.WithdrawMapper
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import com.pawith.domain.user.WithdrawReason
import com.pawith.domain.user.service.WithdrawAppender
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class WithdrawReasonCreateServiceTest:BehaviorSpec({
    val withdrawAppender = mockk<WithdrawAppender>(relaxed=true)
    val withdrawMapper = mockk<WithdrawMapper>()

    val withdrawReasonCreateService = WithdrawReasonCreateService(withdrawAppender, withdrawMapper)


    given("탈퇴 사유 생성 서비스를 테스트한다") {
        val request: WithdrawReasonCreateRequest = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val reason : WithdrawReason = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { withdrawMapper.toWithdrawReasonEntity(request) } returns reason
        `when`("탈퇴 사유를 생성하면") {
            withdrawReasonCreateService.createWithdrawReason(request)
            then("탈퇴 사유가 생성된다") {
                verify(exactly=1) {
                    withdrawAppender.appendWithDraw(reason)
                }
            }
        }
    }
})