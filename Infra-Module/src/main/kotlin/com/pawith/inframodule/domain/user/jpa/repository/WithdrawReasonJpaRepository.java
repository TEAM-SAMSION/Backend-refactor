package com.pawith.inframodule.domain.user.jpa.repository;


import com.pawith.userinfrastructure.jpa.entity.WithdrawReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawReasonJpaRepository extends JpaRepository<WithdrawReasonEntity, Long> {
}
