package com.pawith.domain.auth.jwt.exception

import com.pawith.commonmodule.exception.BusinessException
import com.pawith.commonmodule.exception.Error

open class TokenException(error: Error) : BusinessException(error)
