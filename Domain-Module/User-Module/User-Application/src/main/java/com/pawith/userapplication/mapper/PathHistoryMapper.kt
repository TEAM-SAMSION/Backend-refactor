package com.pawith.userapplication.mapper

import com.pawith.commonmodule.annotation.Mapper
import com.pawith.userapplication.dto.request.PathHistoryCreateRequest
import com.pawith.userdomain.PathHistory

@Mapper
class PathHistoryMapper {

    fun toPathHistoryEntity(request: PathHistoryCreateRequest) : PathHistory {
        return PathHistory.createNewPath(request.path)
    }
}