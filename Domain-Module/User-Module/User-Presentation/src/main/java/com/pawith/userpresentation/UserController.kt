package com.pawith.userpresentation

import com.pawith.userapplication.dto.request.PathHistoryCreateRequest
import com.pawith.userapplication.dto.request.UserNicknameModifyRequest
import com.pawith.userapplication.dto.request.WithdrawReasonCreateRequest
import com.pawith.userapplication.service.*
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/user")
class UserController(
    val userNicknameChangeService: UserNicknameChangeService,
    val userInfoGetService: UserInfoGetService,
    val userProfileImageUpdateService: UserProfileImageUpdateService,
    val pathHistoryCreateService: PathHistoryCreateService,
    val userDeleteService: UserDeleteService,
    val withdrawReasonCreateService: WithdrawReasonCreateService
) {

    @PutMapping("/name")
    fun putNicknameOnUser(
        @RequestBody request: UserNicknameModifyRequest
    ) = userNicknameChangeService.changeUserNickname(request)


    @GetMapping
    fun getUserInfo() = userInfoGetService.getUserInfo()

    @PostMapping(consumes = ["multipart/form-data"])
    fun postUserProfileImage(
        @RequestPart(name = "profileImage") requestImage: MultipartFile
    ) = userProfileImageUpdateService.updateUserProfileImage(requestImage)

    @PostMapping("/path")
    fun postPathHistory(
        @RequestBody request: PathHistoryCreateRequest
    ) = pathHistoryCreateService.createPathHistory(request)


    @DeleteMapping
    fun deleteUser() = userDeleteService.deleteUser()

    @GetMapping("/term")
    fun getTerm() = userInfoGetService.getUserInfo()

    @PostMapping("/withdraw")
    fun postWithdrawReason(
        @RequestBody request: WithdrawReasonCreateRequest
    ) = withdrawReasonCreateService.createWithdrawReason(request)

}