package com.pawith.userapplication.mapper

import com.pawith.commonmodule.annotation.Mapper
import com.pawith.userapplication.dto.request.PathHistoryCreateRequest
import com.pawith.userdomain.entity.PathHistory

@Mapper
class PathHistoryMapper {

    fun toPathHistoryEntity(request: PathHistoryCreateRequest) : PathHistory{
        return PathHistory(
            request.path
        )
    }
}