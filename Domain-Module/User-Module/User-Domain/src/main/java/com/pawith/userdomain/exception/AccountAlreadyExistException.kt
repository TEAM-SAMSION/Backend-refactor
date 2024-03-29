package com.pawith.userdomain.exception

import com.pawith.commonmodule.exception.BusinessException
import com.pawith.commonmodule.exception.Error

class AccountAlreadyExistException(error: Error) : BusinessException(error)
