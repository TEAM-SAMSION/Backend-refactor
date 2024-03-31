package com.pawith.userdomain.exception

import com.pawith.commonmodule.exception.Error
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class UserError(
    val message: String,
    val errorCode: Int,
    val httpStatus: HttpStatusCode
) : Error {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", 2000, HttpStatus.NOT_FOUND),
    ACCOUNT_ALREADY_EXIST("이미 가입한 계정이 있습니다", 2002, HttpStatus.BAD_REQUEST);

    override fun getErrorCode(): Int {
        return errorCode
    }

    override fun getHttpStatusCode(): HttpStatusCode {
        return httpStatus
    }

    override fun getMessage(): String {
        return message
    }

}