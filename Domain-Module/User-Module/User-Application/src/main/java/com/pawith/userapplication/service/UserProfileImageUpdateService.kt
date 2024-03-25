package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.imagemodule.service.ImageUploadService
import com.pawith.userdomain.utils.UserUtils
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@ApplicationService
@Transactional
class UserProfileImageUpdateService (
    val userUtils: UserUtils,
    val imageUploadService: ImageUploadService
){

    fun updateUserProfileImage(image: MultipartFile){
        val imageUrl = imageUploadService.uploadImg(image)
        val user = userUtils.accessUser
        user.updateProfileImage(imageUrl)
    }


}