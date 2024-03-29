package com.pawith.userdomain.exception

import com.pawith.commonmodule.exception.BusinessException
import com.pawith.commonmodule.exception.Error

open class UserException(error: Error) : BusinessException(error)
