package com.pawith.commonmodule.domain

import java.time.LocalDateTime

abstract class BaseDomain(
    val createdDate: LocalDateTime,
    var updatedDate: LocalDateTime
) {
    protected fun updateModifiedDateToCurrent() {
        this.updatedDate = LocalDateTime.now()
    }
}