package com.pawith.inframodule.domain.user;

import com.pawith.domain.User;
import com.pawith.domain.repository.UserRepository;
import com.pawith.userinfrastructure.jpa.repository.UserJpaRepository;
import com.pawith.userinfrastructure.mapper.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Nullable
    @Override
    public User findByEmailOrNull(@NotNull String email) {
        return userJpaRepository.findByEmail(email)
            .map(userPersistenceMapper::toDomain)
            .orElse(null);
    }

    @Nullable
    @Override
    public User findByIdOrNull(long id) {
        return userJpaRepository.findById(id)
            .map(userPersistenceMapper::toDomain)
            .orElse(null);
    }

    @Override
    public boolean existsByEmail(@NotNull String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @NotNull
    @Override
    public List<User> findAllByIds(@NotNull List<Long> ids) {
        return userJpaRepository.findAllByIds(ids).stream()
            .map(userPersistenceMapper::toDomain)
            .toList();
    }

    @NotNull
    @Override
    public List<User> findAllByNicknameAndIds(@NotNull String nickname, @NotNull List<Long> ids) {
        return userJpaRepository.findAllByNicknameAndIds(nickname, ids).stream()
            .map(userPersistenceMapper::toDomain)
            .toList();
    }

    @NotNull
    @Override
    public User save(@NotNull User user) {
        return userPersistenceMapper.toDomain(userJpaRepository.save(userPersistenceMapper.toEntity(user)));
    }

    @NotNull
    @Override
    public User update(@NotNull User user) {
        return userPersistenceMapper.toDomain(userJpaRepository.save(userPersistenceMapper.toEntity(user)));
    }

    @Override
    public void deleteById(long id) {
        userJpaRepository.deleteById(id);
    }
}
