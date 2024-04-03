package com.pawith.inframodule.domain.user.jpa.repository;

import com.pawith.userinfrastructure.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("select u from UserEntity u where u.id in :ids")
    List<UserEntity> findAllByIds(List<Long> ids);

    @Query("select u from UserEntity u where u.nickname like %:nickname% and u.id in :ids")
    List<UserEntity> findAllByNicknameAndIds(String nickname, List<Long> ids);

}