package com.pawith.domain.user

import com.pawith.commonmodule.domain.BaseDomain
import java.time.LocalDateTime

class WithdrawReason(
    val id: Long?,
    val reason: String,
    createdDate: LocalDateTime,
    updatedDate: LocalDateTime
) : BaseDomain(createdDate, updatedDate) {

    companion object{
        fun createNewReason(reason: String) =
            com.pawith.domain.user.WithdrawReason(null, reason, LocalDateTime.now(), LocalDateTime.now())
    }
}