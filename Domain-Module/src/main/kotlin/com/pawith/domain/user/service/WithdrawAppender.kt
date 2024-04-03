package com.pawith.domain.user.service

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.userdomain.WithdrawReason
import com.pawith.userdomain.repository.WithdrawReasonRepository

@DomainService
class WithdrawAppender(
    private val withdrawReasonRepository: WithdrawReasonRepository
) {

    fun appendWithDraw(withdrawReason: WithdrawReason){
        withdrawReasonRepository.save(withdrawReason)
    }
}