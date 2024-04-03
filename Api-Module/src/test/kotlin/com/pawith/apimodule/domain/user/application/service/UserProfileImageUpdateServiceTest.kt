package com.pawith.apimodule.domain.user.application.service

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import com.pawith.domain.user.UserUtils
import com.pawith.domain.user.service.UserModifier
import com.pawith.imagemodule.service.ImageUploadService
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.mock.web.MockMultipartFile

class UserProfileImageUpdateServiceTest : BehaviorSpec({
    val userUtils = mockk<UserUtils>()
    val userModifier = mockk<UserModifier>(relaxed = true)
    val imageUploadService = mockk<ImageUploadService>()

    val userProfileImageUpdateService = UserProfileImageUpdateService(userUtils, userModifier, imageUploadService)

    given("유저 프로필 이미지 업데이트 서비스를 테스트한다") {
        val mockMultiPartFile = MockMultipartFile("file", "test.jpg", "image/jpeg", "test data".toByteArray())
        val mockImageUrl: String = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val mockUserId: Long = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()

        every { userUtils.getIdFromAccessUser() } returns mockUserId
        every { imageUploadService.uploadImg(mockMultiPartFile) } returns mockImageUrl
        `when`("유저 프로필 이미지를 업데이트하면") {
            userProfileImageUpdateService.updateUserProfileImage(mockMultiPartFile)
            then("유저 프로필 이미지가 업데이트된다") {
                verify(exactly = 1) { userModifier.modifyProfileImage(mockUserId, mockImageUrl) }
            }
        }
    }
})