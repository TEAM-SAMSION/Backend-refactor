package com.pawith.userinfrastructure.repository;

import com.pawith.userinfrastructure.entity.PathHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathHistoryRepository extends JpaRepository<PathHistory, Long> {
}
