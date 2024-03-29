package com.pawith.userinfrastructure.mapper;

import com.pawith.commonmodule.annotation.Mapper;
import com.pawith.userdomain.WithdrawReason;
import com.pawith.userinfrastructure.jpa.entity.WithdrawReasonEntity;

@Mapper
public class WithdrawReasonPersistenceMapper {

    public WithdrawReason toDomain(WithdrawReasonEntity entity) {
        return new WithdrawReason(
            entity.getId(),
            entity.getReason(),
            entity.getCreatedDate(),
            entity.getUpdatedDate()
        );
    }

    public WithdrawReasonEntity toEntity(WithdrawReason domain) {
        return new WithdrawReasonEntity(
            domain.getId(),
            domain.getReason(),
            domain.getCreatedDate(),
            domain.getUpdatedDate()
        );
    }
}
