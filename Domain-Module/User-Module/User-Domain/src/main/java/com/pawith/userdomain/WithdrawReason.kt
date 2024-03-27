package com.pawith.userdomain

import com.pawith.commonmodule.domain.BaseDomain
import java.time.LocalDateTime

class WithdrawReason(
    val id: Long = 0,
    val reason: String,
    createdDate: LocalDateTime,
    updatedDate: LocalDateTime
) : BaseDomain(createdDate, updatedDate) {

    private constructor(reason: String) : this(reason = reason,createdDate=LocalDateTime.now(), updatedDate = LocalDateTime.now())

    companion object{
        fun createNewReason(reason: String) = WithdrawReason(reason)
    }
}