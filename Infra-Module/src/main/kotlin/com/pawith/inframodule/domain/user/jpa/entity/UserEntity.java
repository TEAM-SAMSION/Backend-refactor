package com.pawith.inframodule.domain.user.jpa.entity;

import com.pawith.commonmodule.domain.BaseEntity;
import com.pawith.commonmodule.enums.Provider;
import com.pawith.domain.Authority;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Getter
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE user_id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "user")
//@Table(name = "user", indexes = {
////    @Index(name = "idx_user_email", columnList = "email")
//})
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name="nickname", nullable = false)
    private String nickname;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="imageUrl", nullable = false)
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    @Deprecated(since = "2.0")
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private Boolean isDeleted = Boolean.FALSE;

    protected UserEntity() {
        super();
    }

    public UserEntity(Long id,
                      String nickname,
                      String email,
                      String imageUrl,
                      Provider provider,
                      Authority authority,
                      Boolean isDeleted,
                      LocalDateTime createdAt,
                      LocalDateTime updateAt) {
        super(createdAt, updateAt);
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.authority = authority;
        this.isDeleted = isDeleted;
    }

}
