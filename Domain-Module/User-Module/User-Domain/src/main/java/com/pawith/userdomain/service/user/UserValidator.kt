package com.pawith.userdomain.service.user

import com.pawith.commonmodule.annotation.DomainService
import com.pawith.commonmodule.enums.Provider
import com.pawith.userdomain.exception.AccountAlreadyExistException
import com.pawith.userdomain.exception.UserError
import com.pawith.userdomain.repository.UserRepository

@DomainService
class UserValidator(
    private val userRepository: UserRepository,
    private val userReader: UserReader
) {

    fun validateUserAlreadyExist(email :String, provider: Provider){
        userRepository.findByEmail(email)?.let {
            if(it.isMatchingProvider(provider)){
                throw AccountAlreadyExistException(UserError.ACCOUNT_ALREADY_EXIST)
            }
        }
    }

    fun isEmailExist(email: String) : Boolean{
        return userRepository.existsByEmail(email);
    }
}