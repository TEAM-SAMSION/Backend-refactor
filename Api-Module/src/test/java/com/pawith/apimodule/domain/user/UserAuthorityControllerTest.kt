package com.pawith.apimodule.domain.user

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.commonmodule.BaseRestDocsTest
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import com.pawith.userapplication.dto.response.UserAuthorityInfoResponse
import com.pawith.userapplication.service.UserAuthorityGetService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(controllers = [UserAuthorityController::class])
class UserAuthorityControllerTest : BaseRestDocsTest(){

    @MockBean
    private lateinit var userAuthorityGetService: UserAuthorityGetService

    companion object{
        const val USER_AUTHORITY_URL = "/user/authority"
    }

    @Test
    fun `GET getAuthority 200 OK`() {
        //given
        val userAuthorityInfoResponse: UserAuthorityInfoResponse = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        BDDMockito.given(userAuthorityGetService.getUserAuthority()).willReturn(userAuthorityInfoResponse)
        val request = RestDocumentationRequestBuilders.get(USER_AUTHORITY_URL)
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

