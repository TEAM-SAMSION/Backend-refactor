package com.pawith.domain.auth.jwt.exception

import com.pawith.commonmodule.exception.Error

class ExpiredTokenException(error: Error) : TokenException(error)
