package com.pawith.domain.user.exception

import com.pawith.commonmodule.exception.BusinessException
import com.pawith.commonmodule.exception.Error

class AccountAlreadyExistException(error: Error) : BusinessException(error)
