package com.pawith.inframodule.domain.user;

import com.pawith.domain.WithdrawReason;
import com.pawith.domain.repository.WithdrawReasonRepository;
import com.pawith.userinfrastructure.jpa.repository.WithdrawReasonJpaRepository;
import com.pawith.userinfrastructure.mapper.WithdrawReasonPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WithdrawReasonRepositoryImpl implements WithdrawReasonRepository {
    private final WithdrawReasonJpaRepository withdrawReasonJpaRepository;
    private final WithdrawReasonPersistenceMapper mapper;
    @NotNull
    @Override
    public WithdrawReason save(@NotNull WithdrawReason withdrawReason) {
        return mapper.toDomain(withdrawReasonJpaRepository.save(mapper.toEntity(withdrawReason)));
    }
}
