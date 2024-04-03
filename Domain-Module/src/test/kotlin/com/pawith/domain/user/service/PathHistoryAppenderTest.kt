package com.pawith.domain.user.service

import com.pawith.domain.user.repository.PathHistoryRepository
import com.pawith.domain.user.service.PathHistoryAppender
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
class PathHistoryAppenderTest: BehaviorSpec({
    val pathHistoryRepository = mockk<PathHistoryRepository>()
    val pathHistoryAppender = PathHistoryAppender(pathHistoryRepository)

    given("PathHistoryAppender의 appendHistory 메소드를 테스트한다") {
        `when`("PathHistory를 저장하면") {
            pathHistoryAppender.appendHistory(mockk())
            then("PathHistoryRepository의 save 메소드가 호출된다") {
                 verify(exactly = 1) { pathHistoryRepository.save(any()) }
            }
        }
    }
})