package com.pawith.userinfrastructure.repository;


import com.pawith.userinfrastructure.entity.WithdrawReason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawReasonRepository extends JpaRepository<WithdrawReason, Long> {
}
