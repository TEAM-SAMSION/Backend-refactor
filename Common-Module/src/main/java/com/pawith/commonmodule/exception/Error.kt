package com.pawith.commonmodule.exception

import org.springframework.http.HttpStatusCode

interface Error {
    val message: String
    val errorCode: Int
    val httpStatusCode: HttpStatusCode
}