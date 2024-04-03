package com.pawith.apimodule.domain.user.application.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.request.WithdrawReasonCreateRequest
import com.pawith.userapplication.mapper.WithdrawMapper
import com.pawith.userdomain.service.WithdrawAppender
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional
class WithdrawReasonCreateService (
    private val withdrawAppender: WithdrawAppender,
    private val withdrawMapper: WithdrawMapper
){

    fun createWithdrawReason(request: WithdrawReasonCreateRequest){
        val reason = withdrawMapper.toWithdrawReasonEntity(request)
        withdrawAppender.appendWithDraw(reason)
    }
}