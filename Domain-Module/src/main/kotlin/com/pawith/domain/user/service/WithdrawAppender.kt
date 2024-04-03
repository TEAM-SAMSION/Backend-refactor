package com.pawith.domain.user.service

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.domain.user.WithdrawReason
import com.pawith.domain.user.repository.WithdrawReasonRepository

@DomainService
class WithdrawAppender(
    private val withdrawReasonRepository: WithdrawReasonRepository
) {

    fun appendWithDraw(withdrawReason: WithdrawReason){
        withdrawReasonRepository.save(withdrawReason)
    }
}