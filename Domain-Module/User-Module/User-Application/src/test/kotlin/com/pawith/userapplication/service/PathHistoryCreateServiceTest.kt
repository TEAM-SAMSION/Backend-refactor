package com.pawith.userapplication.service

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import com.pawith.userapplication.dto.request.PathHistoryCreateRequest
import com.pawith.userapplication.mapper.PathHistoryMapper
import com.pawith.userdomain.PathHistory
import com.pawith.userdomain.service.PathHistoryAppender
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class PathHistoryCreateServiceTest : BehaviorSpec({
    val pathHistoryAppender = mockk<PathHistoryAppender>(relaxed = true)
    val pathHistoryMapper = mockk<PathHistoryMapper>()

    val pathHistoryCreateService = PathHistoryCreateService(pathHistoryAppender, pathHistoryMapper)

    given("가입 경로 생성 요청이"){
        val pathHistoryCreateRequest : PathHistoryCreateRequest = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val pathHistory: PathHistory = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { pathHistoryMapper.toPathHistoryEntity(pathHistoryCreateRequest)} returns pathHistory
        `when`("정상으로 들어오면"){
            pathHistoryCreateService.createPathHistory(pathHistoryCreateRequest)
            then("가입 경로를 저장한다."){
                verify(exactly = 1) { pathHistoryAppender.appendHistory(pathHistory) }
            }
        }
    }
})