package com.pawith.inframodule.domain.user.jpa.repository;

import com.pawith.userinfrastructure.jpa.entity.PathHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathHistoryJpaRepository extends JpaRepository<PathHistoryEntity, Long> {
}
