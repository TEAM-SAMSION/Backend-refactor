package com.pawith.userdomain.exception

import com.pawith.commonmodule.exception.Error

class UserAuthorityNotFoundException(error: Error) : UserException(error)
