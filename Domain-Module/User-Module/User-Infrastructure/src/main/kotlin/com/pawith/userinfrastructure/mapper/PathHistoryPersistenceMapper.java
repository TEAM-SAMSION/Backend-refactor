package com.pawith.userinfrastructure.mapper;

import com.pawith.commonmodule.annotation.Mapper;
import com.pawith.userdomain.PathHistory;
import com.pawith.userinfrastructure.jpa.entity.PathHistoryEntity;

@Mapper
public class PathHistoryPersistenceMapper {

    public PathHistory toDomain(PathHistoryEntity entity) {
        return new PathHistory(
            entity.getId(),
            entity.getPath(),
            entity.getCreatedDate(),
            entity.getUpdatedDate()
        );
    }

    public PathHistoryEntity toEntity(PathHistory domain) {
        return new PathHistoryEntity(
            domain.getId(),
            domain.getPath(),
            domain.getCreatedDate(),
            domain.getUpdatedDate()
        );
    }
}
