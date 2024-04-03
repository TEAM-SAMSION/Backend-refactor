package com.pawith.domain.todo.entity;

import com.pawith.commonmodule.domain.BaseEntity;
import com.pawith.commonmodule.util.DomainFieldUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE category SET is_deleted = true WHERE category_id = ?")
@Where(clause = "is_deleted = false")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryStatus categoryStatus;

    private Boolean isDeleted=Boolean.FALSE;

    private LocalDate disabledAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private com.pawith.tododomain.entity.TodoTeam todoTeam;

    @Builder
    public Category(String name, com.pawith.tododomain.entity.CategoryStatus categoryStatus, com.pawith.tododomain.entity.TodoTeam todoTeam) {
        this.name = name;
        this.categoryStatus = categoryStatus;
        this.todoTeam = todoTeam;
        this.disabledAt = LocalDate.now();
    }

    public void updateCategoryStatus() {
        this.categoryStatus = DomainFieldUtils.DomainValidateBuilder.builder(com.pawith.tododomain.entity.CategoryStatus.class)
                .newValue(this.categoryStatus.equals(com.pawith.tododomain.entity.CategoryStatus.ON) ? com.pawith.tododomain.entity.CategoryStatus.OFF : com.pawith.tododomain.entity.CategoryStatus.ON)
                .currentValue(this.categoryStatus)
                .validate();

        this.disabledAt = DomainFieldUtils.DomainValidateBuilder.builder(LocalDate.class)
                .newValue(LocalDate.now())
                .currentValue(this.disabledAt)
                .validate();
    }

    public void updateCategoryName(String categoryName) {
        this.name = DomainFieldUtils.DomainValidateBuilder.builder(String.class)
                .newValue(categoryName)
                .currentValue(this.name)
                .validate();
    }
}
