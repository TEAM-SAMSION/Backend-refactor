package com.pawith.userapplication.service

import com.pawith.commonmodule.annotation.ApplicationService
import com.pawith.commonmodule.event.UserAccountDeleteEvent
import com.pawith.userdomain.service.UserDeleteService
import com.pawith.userdomain.utils.UserUtils
import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional

@ApplicationService
@Transactional
class UserDeleteService(
    val userUtils: UserUtils,
    val userDeleteService: UserDeleteService,
    val applicationEventPublisher: ApplicationEventPublisher
) {

    fun deleteUser() {
        val userId = userUtils.idFromAccessUser
        userDeleteService.deleteUser(userId)
        applicationEventPublisher.publishEvent(UserAccountDeleteEvent(userId))
    }

}