package com.pawith.domain.user.exception

import com.pawith.commonmodule.exception.Error

class UserNotFoundException(error: Error) : UserException(error)
