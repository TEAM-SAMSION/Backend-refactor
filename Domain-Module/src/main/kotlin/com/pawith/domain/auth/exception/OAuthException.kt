package com.pawith.domain.auth.exception

import com.pawith.commonmodule.exception.BusinessException
import com.pawith.commonmodule.exception.Error

open class OAuthException(error: Error) : BusinessException(error)
