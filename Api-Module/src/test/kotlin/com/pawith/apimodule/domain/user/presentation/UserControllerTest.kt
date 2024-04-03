package com.pawith.apimodule.domain.user.presentation

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.apimodule.domain.user.application.dto.request.PathHistoryCreateRequest
import com.pawith.apimodule.domain.user.application.dto.request.UserNicknameModifyRequest
import com.pawith.apimodule.domain.user.application.dto.request.WithdrawReasonCreateRequest
import com.pawith.apimodule.domain.user.application.dto.response.UserAuthorityInfoResponse
import com.pawith.apimodule.domain.user.application.dto.response.UserInfoResponse
import com.pawith.apimodule.domain.user.application.dto.response.UserJoinTermResponse
import com.pawith.apimodule.domain.user.application.service.*
import com.pawith.apimodule.global.consts.ApiSpec
import com.pawith.commonmodule.BaseRestDocsTest
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.mock.web.MockMultipartFile
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.partWithName
import org.springframework.restdocs.request.RequestDocumentation.requestParts
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
class UserControllerTest : BaseRestDocsTest() {

    @MockBean
    private lateinit var userNicknameChangeService: UserNicknameChangeService

    @MockBean
    private lateinit var userInfoGetService: UserInfoGetService

    @MockBean
    private lateinit var userProfileImageUpdateService: UserProfileImageUpdateService

    @MockBean
    private lateinit var pathHistoryCreateService: PathHistoryCreateService

    @MockBean
    private lateinit var userDeleteService: UserDeleteService

    @MockBean
    private lateinit var withdrawReasonCreateService: WithdrawReasonCreateService

    @MockBean
    private lateinit var userAuthorityGetService: UserAuthorityGetService

    @Test
    fun `PUT putNicknameOnUser 200 OK`() {
        //given
        val userNicknameModifyRequest: UserNicknameModifyRequest =
            FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val request = RestDocumentationRequestBuilders.put("${ApiSpec.User.BASE_URL}${ApiSpec.User.PUT_NICKNAME}")
            .contentType("application/json")
            .header("Authorization", "Bearer AccessToken")
            .content(objectMapper.writeValueAsString(userNicknameModifyRequest))
        //when
        val result = mvc.perform(request)
        //then
        result.andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("access 토큰")
                    ),
                    requestFields(
                        fieldWithPath("nickname").description("변경할 닉네임")
                    )
                )
            )
    }

    @Test
    fun `GET getUserInfo 200 OK`() {
        //given
        val request = RestDocumentationRequestBuilders.get(ApiSpec.User.BASE_URL)
            .header("Authorization", "Bearer AccessToken")
            .contentType("application/json")
        val userInfoResponse: UserInfoResponse = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        BDDMockito.given(userInfoGetService.getUserInfo()).willReturn(userInfoResponse)
        //when
        val result = mvc.perform(request)
        //then
        result.andExpect(status().isOk())
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("access 토큰")
                    ),
                    responseFields(
                        fieldWithPath("nickname").description("유저 닉네임"),
                        fieldWithPath("email").description("유저 이메일"),
                        fieldWithPath("profileImageUrl").description("유저 프로필 이미지")
                    )
                )
            );
    }

    @Test
    fun `POST postUserProfileImage`() {
        //given
        val profileImage = MockMultipartFile("profileImage", "profileImage".toByteArray())
        val request = RestDocumentationRequestBuilders.multipart(ApiSpec.User.BASE_URL)
            .file(profileImage)
            .contentType("multipart/form-data")
            .header("Authorization", "Bearer AccessToken")
        //when
        val result = mvc.perform(request)
        //then
        result.andExpect(status().isOk())
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("access 토큰")
                    ),
                    requestParts(
                        partWithName("profileImage").description("유저 프로필 이미지")
                    )
                )
            );
    }

    @Test
    fun `POST postPathHistory 200 OK`() {
        //given
        val pathHistoryCreateRequest: PathHistoryCreateRequest =
            FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val request = RestDocumentationRequestBuilders.post("${ApiSpec.User.BASE_URL}${ApiSpec.User.POST_PATH_HISTORY}")
            .content(objectMapper.writeValueAsString(pathHistoryCreateRequest))
            .header("Authorization", "Bearer AccessToken")
            .contentType("application/json")
        //when
        val result = mvc.perform(request)
        //then
        result.andExpect(status().isOk())
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("access 토큰")
                    ),
                    requestFields(
                        fieldWithPath("path").description("유저가 포잇을 알게 된 경로")
                    )
                )
            );
    }

    @Test
    fun `DELETE deleteUser 200 OK`() {
        //given
        val request = RestDocumentationRequestBuilders.delete(ApiSpec.User.BASE_URL)
            .header("Authorization", "Bearer AccessToken")
        //when
        val result = mvc.perform(request)
        //then
        result.andExpect(status().isOk())
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("access 토큰")
                    )
                )
            );
    }

    @Test
    fun `GET getTerm 200 OK`() {
        //given
        val request = RestDocumentationRequestBuilders.get("${ApiSpec.User.BASE_URL}${ApiSpec.User.GET_USER_JOIN_TERM}")
            .header("Authorization", "Bearer AccessToken")
            .contentType("application/json")
        val userJoinTermResponse: UserJoinTermResponse = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        BDDMockito.given(userInfoGetService.getUserJoinTerm()).willReturn(userJoinTermResponse)
        //when
        val result = mvc.perform(request)
        //then
        result.andExpect(status().isOk())
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("access 토큰")
                    ),
                    responseFields(
                        fieldWithPath("joinTerm").description("서비스 가입 기간")
                    )
                )
            );
    }

    @Test
    fun `POST postWithdrawReason`() {
        //given
        val withdrawReasonCreateRequest: WithdrawReasonCreateRequest =
            FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val request = RestDocumentationRequestBuilders.post("${ApiSpec.User.BASE_URL}${ApiSpec.User.POST_USER_WITHDRAW_REASON}")
            .content(objectMapper.writeValueAsString(withdrawReasonCreateRequest))
            .header("Authorization", "Bearer AccessToken")
            .contentType("application/json")
        //when
        val result = mvc.perform(request)
        //then
        result.andExpect(status().isOk())
            .andDo(
                resultHandler.document(
                    requestHeaders(
                        headerWithName("Authorization").description("access 토큰")
                    ),
                    requestFields(
                        fieldWithPath("reason").description("탈퇴 이유")
                    )
                )
            );
    }

    @Test
    fun `GET getAuthority 200 OK`() {
        //given
        val userAuthorityInfoResponse: UserAuthorityInfoResponse = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        BDDMockito.given(userAuthorityGetService.getUserAuthority()).willReturn(userAuthorityInfoResponse)
        val request = RestDocumentationRequestBuilders.get(ApiSpec.User.BASE_URL+ApiSpec.User.GET_AUTHORITY)
            .header("Authorization" , "Bearer AccessToken")
        //when
        val result = mvc.perform(request)
        //then
        result.andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andDo(resultHandler.document(
                requestHeaders(
                    headerWithName("Authorization").description("access 토큰")
                ),
                responseFields(
                    fieldWithPath("authority").description("사용자 권한")
                )
            ));
    }

}