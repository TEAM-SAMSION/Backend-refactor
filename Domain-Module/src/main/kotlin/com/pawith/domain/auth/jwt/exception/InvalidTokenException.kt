package com.pawith.domain.auth.jwt.exception

import com.pawith.commonmodule.exception.Error

class InvalidTokenException(error: Error) : TokenException(error)
