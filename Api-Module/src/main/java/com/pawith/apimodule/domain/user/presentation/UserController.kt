package com.pawith.apimodule.domain.user.presentation

import com.pawith.apimodule.domain.user.application.dto.request.PathHistoryCreateRequest
import com.pawith.apimodule.domain.user.application.dto.request.UserNicknameModifyRequest
import com.pawith.apimodule.domain.user.application.dto.request.WithdrawReasonCreateRequest
import com.pawith.apimodule.global.consts.ApiSpec
import com.pawith.apimodule.domain.user.application.dto.response.UserAuthorityInfoResponse
import com.pawith.apimodule.domain.user.application.service.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(ApiSpec.User.BASE_URL)
class UserController(
    private val userNicknameChangeService: UserNicknameChangeService,
    private val userInfoGetService: UserInfoGetService,
    private val userProfileImageUpdateService: UserProfileImageUpdateService,
    private val pathHistoryCreateService: PathHistoryCreateService,
    private val userDeleteService: UserDeleteService,
    private val withdrawReasonCreateService: WithdrawReasonCreateService,
    private val userAuthorityGetUService: UserAuthorityGetService,
) {

    @PutMapping(ApiSpec.User.PUT_NICKNAME)
    fun putNicknameOnUser(
        @RequestBody request: UserNicknameModifyRequest
    ) = userNicknameChangeService.changeUserNickname(request)


    @GetMapping
    fun getUserInfo() = userInfoGetService.getUserInfo()

    @PostMapping(consumes = ["multipart/form-data"])
    fun postUserProfileImage(
        @RequestPart(name = "profileImage") requestImage: MultipartFile
    ) = userProfileImageUpdateService.updateUserProfileImage(requestImage)

    @PostMapping(ApiSpec.User.POST_PATH_HISTORY)
    fun postPathHistory(
        @RequestBody request: PathHistoryCreateRequest
    ) = pathHistoryCreateService.createPathHistory(request)


    @DeleteMapping
    fun deleteUser() = userDeleteService.deleteUser()

    @GetMapping(ApiSpec.User.GET_USER_JOIN_TERM)
    fun getTerm() = userInfoGetService.getUserJoinTerm()

    @PostMapping(ApiSpec.User.POST_USER_WITHDRAW_REASON)
    fun postWithdrawReason(
        @RequestBody request: WithdrawReasonCreateRequest
    ) = withdrawReasonCreateService.createWithdrawReason(request)

    @GetMapping(ApiSpec.User.GET_AUTHORITY)
    fun getAuthority() : UserAuthorityInfoResponse = userAuthorityGetUService.getUserAuthority()

}