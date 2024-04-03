package com.pawith.apimodule.domain.user.application.mapper

import com.pawith.apimodule.domain.user.application.dto.request.PathHistoryCreateRequest
import com.pawith.commonmodule.annotation.Mapper
import com.pawith.userapplication.dto.request.PathHistoryCreateRequest
import com.pawith.userdomain.PathHistory

@Mapper
class PathHistoryMapper {

    fun toPathHistoryEntity(request: PathHistoryCreateRequest) : PathHistory {
        return PathHistory.createNewPath(request.path)
    }
}