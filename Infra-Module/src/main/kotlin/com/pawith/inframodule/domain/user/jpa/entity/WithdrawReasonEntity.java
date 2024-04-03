package com.pawith.inframodule.domain.user.jpa.entity;

import com.pawith.commonmodule.domain.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "withdraw_reason")
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
