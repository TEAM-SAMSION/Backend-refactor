package com.pawith.userdomain.repository

import com.pawith.userdomain.WithdrawReason

interface WithdrawReasonRepository {
    fun save(withdrawReason: WithdrawReason) : WithdrawReason
}