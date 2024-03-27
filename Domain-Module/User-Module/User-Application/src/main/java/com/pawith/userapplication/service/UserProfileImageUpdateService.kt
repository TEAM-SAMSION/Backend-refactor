package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.imagemodule.service.ImageUploadService
import com.pawith.userdomain.UserUtils
import com.pawith.userdomain.service.UserModifier
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@ApplicationService
@Transactional
class UserProfileImageUpdateService (
    private val userUtils: UserUtils,
    private val userModifier: UserModifier,
    private val imageUploadService: ImageUploadService
){

    fun updateUserProfileImage(image: MultipartFile){
        userModifier.modifyProfileImage(
            userUtils.getIdFromAccessUser(),
            imageUploadService.uploadImg(image)
        )
    }


}