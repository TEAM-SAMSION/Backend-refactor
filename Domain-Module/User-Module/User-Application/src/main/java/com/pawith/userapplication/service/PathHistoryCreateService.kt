package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.request.PathHistoryCreateRequest
import com.pawith.userapplication.mapper.PathHistoryMapper
import com.pawith.userdomain.service.PathHistorySaveService
import org.springframework.transaction.annotation.Transactional


@ApplicationService
@Transactional
class PathHistoryCreateService(
    val pathHistorySaveService: PathHistorySaveService,
    val pathHistoryMapper: PathHistoryMapper
) {

    fun createPathHistory(request: PathHistoryCreateRequest) {
        val pathHistory = pathHistoryMapper.toPathHistoryEntity(request)
        pathHistorySaveService.savePathHistoryEntity(pathHistory);
    }
}