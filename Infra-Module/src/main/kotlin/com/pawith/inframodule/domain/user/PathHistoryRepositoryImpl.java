package com.pawith.inframodule.domain.user;

import com.pawith.domain.PathHistory;
import com.pawith.domain.repository.PathHistoryRepository;
import com.pawith.userinfrastructure.jpa.repository.PathHistoryJpaRepository;
import com.pawith.userinfrastructure.mapper.PathHistoryPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PathHistoryRepositoryImpl implements PathHistoryRepository {
    private final PathHistoryJpaRepository pathHistoryJpaRepository;
    private final PathHistoryPersistenceMapper mapper;

    @NotNull
    @Override
    public PathHistory save(@NotNull PathHistory pathHistory) {
        return mapper.toDomain(pathHistoryJpaRepository.save(mapper.toEntity(pathHistory)));
    }
}
