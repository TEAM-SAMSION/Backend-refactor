package com.pawith.userdomain.exception

import com.pawith.commonmodule.exception.Error

class UserNotFoundException(error: Error) : UserException(error)
