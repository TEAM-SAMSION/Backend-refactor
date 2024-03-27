package com.pawith.userinfrastructure.repository;

import com.pawith.userdomain.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {

    Optional<UserAuthority> findByEmail(String email);

    UserAuthority findByUserId(Long userId);
}