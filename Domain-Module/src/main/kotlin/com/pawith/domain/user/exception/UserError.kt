package com.pawith.domain.user.exception

import com.pawith.commonmodule.exception.Error
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class UserError(
    override val message: String,
    override val errorCode: Int,
    override val httpStatusCode: HttpStatusCode
) : Error {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", 2000, HttpStatus.NOT_FOUND),
    ACCOUNT_ALREADY_EXIST("이미 가입한 계정이 있습니다", 2002, HttpStatus.BAD_REQUEST);
}