package com.pawith.domain.user.service

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.pawith.commonmodule.utils.FixtureMonkeyUtils
import com.pawith.domain.user.User
import com.pawith.domain.user.exception.UserNotFoundException
import com.pawith.domain.user.repository.UserRepository
import com.pawith.domain.user.service.UserReader
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UserReaderTest : BehaviorSpec({
    val userRepository = mockk<UserRepository>()
    val userReader = UserReader(userRepository)

    given("UserReader의 readByEmail 메소드를 테스트한다") {
        val email: String = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val user: User = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { userRepository.findByEmailOrNull(email) } returns user
        `when`("User를 읽으면") {
            val readerUser = userReader.readByEmail(email)
            then("UserRepository의 findById 메소드가 호출된다") {
                verify(exactly = 1) { userRepository.findByEmailOrNull(email) }
                readerUser shouldBe user
            }
        }

        every { userRepository.findByEmailOrNull(email) } returns null
        `when`("User를 읽을때 없으면") {
            then("UserNotFoundException이 발생한다") {
                shouldThrow<UserNotFoundException> {
                    userReader.readByEmail(email)
                }
            }
        }
    }

    given("UserReader의 readById 메소드를 테스트한다") {
        val id: Long = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val user: User = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { userRepository.findByIdOrNull(id) } returns user
        `when`("User를 읽으면") {
            val readerUser = userReader.readByUserId(id)
            then("UserRepository의 findById 메소드가 호출된다") {
                verify(exactly = 1) { userRepository.findByIdOrNull(id) }
                readerUser shouldBe user
            }
        }

        every { userRepository.findByIdOrNull(id) } returns null
        `when`("User를 읽을때 없으면") {
            then("UserNotFoundException이 발생한다") {
                shouldThrow<UserNotFoundException> {
                    userReader.readByUserId(id)
                }
            }
        }
    }

    given("UserReader의 readUserMapByIds 메소드를 테스트한다") {
        val ids: List<Long> = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val users: List<User> = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { userRepository.findAllByIds(ids) } returns users
        `when`("User를 읽으면") {
            val readerUser = userReader.readUsersMapByIds(ids)
            then("UserRepository의 findById 메소드가 호출된다") {
                verify(exactly = 1) { userRepository.findAllByIds(ids) }
                readerUser shouldBe users.associateBy { it.id!! }
            }
        }
    }

    given("UserReader의 readAllByNicknameAndUserIds 메소드를 테스트한다") {
        val nickname: String = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val ids: List<Long> = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        val users: List<User> = FixtureMonkeyUtils.getKotlinBasedFixtureMonkey().giveMeOne()
        every { userRepository.findAllByNicknameAndIds(nickname, ids) } returns users
        `when`("User를 읽으면") {
            val readerUser = userReader.readAllByNicknameAndUserIds(nickname, ids)
            then("UserRepository의 findById 메소드가 호출된다") {
                verify(exactly = 1) { userRepository.findAllByNicknameAndIds(nickname, ids) }
                readerUser shouldBe users
            }
        }
    }

})