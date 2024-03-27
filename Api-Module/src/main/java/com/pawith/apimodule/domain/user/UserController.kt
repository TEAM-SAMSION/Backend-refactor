package com.pawith.apimodule.domain.user

import com.pawith.userapplication.dto.request.PathHistoryCreateRequest
import com.pawith.userapplication.dto.request.UserNicknameModifyRequest
import com.pawith.userapplication.dto.request.WithdrawReasonCreateRequest
import com.pawith.userapplication.service.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/users")
class UserController(
    private val userNicknameChangeService: UserNicknameChangeService,
    private val userInfoGetService: UserInfoGetService,
    private val userProfileImageUpdateService: UserProfileImageUpdateService,
    private val pathHistoryCreateService: PathHistoryCreateService,
    private val userDeleteService: UserDeleteService,
    private val withdrawReasonCreateService: WithdrawReasonCreateService
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
    fun getTerm() = userInfoGetService.getUserJoinTerm()

    @PostMapping("/withdraw")
    fun postWithdrawReason(
        @RequestBody request: WithdrawReasonCreateRequest
    ) = withdrawReasonCreateService.createWithdrawReason(request)

}