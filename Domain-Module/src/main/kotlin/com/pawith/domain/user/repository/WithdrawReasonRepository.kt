package com.pawith.domain.user.repository

import com.pawith.domain.user.WithdrawReason


interface WithdrawReasonRepository {
    fun save(withdrawReason: WithdrawReason) : WithdrawReason
}