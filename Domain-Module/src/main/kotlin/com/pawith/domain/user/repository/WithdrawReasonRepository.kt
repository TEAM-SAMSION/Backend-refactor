package com.pawith.domain.user.repository

import com.pawith.userdomain.WithdrawReason

interface WithdrawReasonRepository {
    fun save(withdrawReason: WithdrawReason) : WithdrawReason
}