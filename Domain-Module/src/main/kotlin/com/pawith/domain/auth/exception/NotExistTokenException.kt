package com.pawith.domain.auth.exception

import com.pawith.commonmodule.exception.Error

class NotExistTokenException(error: Error) : TokenException(error)
