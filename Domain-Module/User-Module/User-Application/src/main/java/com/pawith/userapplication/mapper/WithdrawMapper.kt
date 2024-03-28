package com.pawith.userapplication.mapper

import com.pawith.commonmodule.annotation.Mapper
import com.pawith.userapplication.dto.request.WithdrawReasonCreateRequest
import com.pawith.userdomain.WithdrawReason

@Mapper
class WithdrawMapper {

    fun toWithdrawReasonEntity(request: WithdrawReasonCreateRequest): WithdrawReason {
        return WithdrawReason.createNewReason(request.reason)
    }
}