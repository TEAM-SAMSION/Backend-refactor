package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.commonmodule.event.UserAccountDeleteEvent
import com.pawith.userdomain.UserUtils
import com.pawith.userdomain.service.UserRemover
import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional
class UserDeleteService(
    private val userUtils: UserUtils,
    private val userRemover: UserRemover,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun deleteUser() {
        val userId = userUtils.getIdFromAccessUser()
        userRemover.removeUser(userId)
        applicationEventPublisher.publishEvent(UserAccountDeleteEvent(userId))
    }

}