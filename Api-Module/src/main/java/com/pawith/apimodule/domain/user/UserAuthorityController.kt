package com.pawith.apimodule.domain.user

import com.pawith.userapplication.dto.response.UserAuthorityInfoResponse
import com.pawith.userapplication.service.UserAuthorityGetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserAuthorityController (
    private val userAuthorityGetUService: UserAuthorityGetService,
){

    @GetMapping("/authority")
    fun getAuthority() : UserAuthorityInfoResponse = userAuthorityGetUService.getUserAuthority()
}