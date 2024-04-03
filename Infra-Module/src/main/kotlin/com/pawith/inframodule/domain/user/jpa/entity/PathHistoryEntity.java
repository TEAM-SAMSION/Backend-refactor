package com.pawith.inframodule.domain.user.jpa.entity;

import com.pawith.commonmodule.domain.BaseEntity;

import java.time.LocalDateTime;


@Entity
@Getter
@Table(name = "path_history")
public class PathHistoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "path_history_id")
    private Long id;

    private String path;

    public PathHistoryEntity(Long id, String path, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdDate, updatedDate);
        this.id = id;
        this.path = path;
    }

    protected PathHistoryEntity() {
        super();
    }
}
