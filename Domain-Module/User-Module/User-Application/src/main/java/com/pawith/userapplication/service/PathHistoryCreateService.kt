package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.request.PathHistoryCreateRequest
import com.pawith.userapplication.mapper.PathHistoryMapper
import com.pawith.userdomain.service.PathHistoryAppender
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