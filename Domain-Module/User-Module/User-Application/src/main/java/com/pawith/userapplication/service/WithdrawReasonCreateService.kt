package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.userapplication.dto.request.WithdrawReasonCreateRequest
import com.pawith.userapplication.mapper.WithdrawMapper
import com.pawith.userdomain.service.WithDrawSaveService
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional
class WithdrawReasonCreateService (
    val withDrawSaveService: WithDrawSaveService,
    val withdrawMapper: WithdrawMapper
){

    fun createWithdrawReason(request: WithdrawReasonCreateRequest){
        val reason = withdrawMapper.toWithdrawReasonEntity(request)
        withDrawSaveService.save(reason)
    }
}