package com.pawith.domain.user.service

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.domain.user.PathHistory
import com.pawith.domain.user.repository.PathHistoryRepository

@DomainService
class PathHistoryAppender(
    val pathHistoryRepository: PathHistoryRepository
) {

    fun appendHistory(pathHistory: PathHistory){
        pathHistoryRepository.save(pathHistory)
    }
}