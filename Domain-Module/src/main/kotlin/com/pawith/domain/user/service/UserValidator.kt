package com.pawith.domain.user.service

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.commonmodule.enums.Provider
import com.pawith.userdomain.exception.AccountAlreadyExistException
import com.pawith.userdomain.exception.UserError
import com.pawith.userdomain.repository.UserRepository

@DomainService
class UserValidator(
    private val userRepository: UserRepository
) {

    fun validateUserAlreadyExist(email :String, provider: Provider){
        userRepository.findByEmailOrNull(email)?.let {
            if(it.isProviderMatching(provider)){
                throw AccountAlreadyExistException(UserError.ACCOUNT_ALREADY_EXIST)
            }
        }
    }

    fun isEmailExist(email: String) : Boolean{
        return userRepository.existsByEmail(email);
    }
}