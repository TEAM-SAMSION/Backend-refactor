package com.pawith.userdomain

import com.pawith.commonmodule.domain.BaseDomain
import java.time.LocalDateTime

class PathHistory(
    val id: Long?,
    val path: String,
    createdDate: LocalDateTime,
    updatedDate: LocalDateTime
) : BaseDomain(createdDate, updatedDate){

    private constructor(path: String):this(0, path, LocalDateTime.now(), LocalDateTime.now())

    companion object{
        fun createNewPath(path: String) = PathHistory(path)
    }
}