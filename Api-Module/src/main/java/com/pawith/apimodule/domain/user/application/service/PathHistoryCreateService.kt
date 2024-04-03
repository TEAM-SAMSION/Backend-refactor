package com.pawith.apimodule.domain.user.application.service

import com.pawith.apimodule.domain.user.application.dto.request.PathHistoryCreateRequest
import com.pawith.apimodule.domain.user.application.mapper.PathHistoryMapper
import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.domain.user.service.PathHistoryAppender
import org.springframework.transaction.annotation.Transactional


@ApplicationService
@Transactional
class PathHistoryCreateService(
    private val pathHistoryAppender: PathHistoryAppender,
    private val pathHistoryMapper: PathHistoryMapper
) {

    fun createPathHistory(request: PathHistoryCreateRequest) {
        val pathHistory = pathHistoryMapper.toPathHistoryEntity(request)
        pathHistoryAppender.appendHistory(pathHistory);
    }
}