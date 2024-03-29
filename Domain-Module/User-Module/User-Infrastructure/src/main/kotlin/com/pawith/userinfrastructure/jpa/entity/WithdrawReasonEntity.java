package com.pawith.userinfrastructure.jpa.entity;

import com.pawith.commonmodule.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
public class WithdrawReasonEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "withdraw_reason_id")
    private Long id;

    private String reason;

    public WithdrawReasonEntity(Long id, String reason, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdDate, updatedDate);
        this.id = id;
        this.reason = reason;
    }

    protected WithdrawReasonEntity() {
    }
}
