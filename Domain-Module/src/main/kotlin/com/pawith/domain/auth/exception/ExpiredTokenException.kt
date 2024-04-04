package com.pawith.domain.auth.exception

import com.pawith.commonmodule.exception.Error

class ExpiredTokenException(error: Error) : TokenException(error)
