package com.pawith.domain.todo.entity;

import com.pawith.commonmodule.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE assign SET is_deleted = true WHERE assign_id=?")
@Where(clause = "is_deleted=false")
public class Assign extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assign_id")
    private Long id;

    private Boolean isDeleted = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    private com.pawith.tododomain.entity.CompletionStatus completionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private com.pawith.tododomain.entity.Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id")
    private com.pawith.tododomain.entity.Register register;

    @Builder
    public Assign(com.pawith.tododomain.entity.Todo todo, com.pawith.tododomain.entity.Register register) {
        this.todo = todo;
        this.register = register;
        this.completionStatus = com.pawith.tododomain.entity.CompletionStatus.INCOMPLETE;
    }

    public void updateCompletionStatus() {
        if(this.completionStatus.equals(com.pawith.tododomain.entity.CompletionStatus.COMPLETE))
            this.completionStatus = com.pawith.tododomain.entity.CompletionStatus.INCOMPLETE;
        else
            this.completionStatus = com.pawith.tododomain.entity.CompletionStatus.COMPLETE;
    }

    public Boolean isCompleted() {
        return this.completionStatus.equals(com.pawith.tododomain.entity.CompletionStatus.COMPLETE);
    }
}
