package com.pawith.inframodule.domain.user.mapper;

import com.pawith.commonmodule.annotation.Mapper;
import com.pawith.domain.User;
import com.pawith.domain.UserAuthority;
import com.pawith.userinfrastructure.jpa.entity.UserEntity;

@Mapper
public class UserPersistenceMapper {

    public User toDomain(UserEntity userEntity){
        return new User(
            userEntity.getId(),
            userEntity.getNickname(),
            userEntity.getEmail(),
            userEntity.getImageUrl(),
            userEntity.getProvider(),
            new UserAuthority(userEntity.getAuthority()),
            userEntity.getIsDeleted(),
            userEntity.getCreatedDate(),
            userEntity.getUpdatedDate()
        );
    }

    public UserEntity toEntity(User user){
        return new UserEntity(
            user.getId(),
            user.getNickname(),
            user.getEmail(),
            user.getImageUrl(),
            user.getProvider(),
            user.getUserAuthority().getAuthority(),
            user.isDeleted(),
            user.getCreatedDate(),
            user.getUpdatedDate()
        );
    }
}
