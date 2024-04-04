package com.pawith.domain.auth.exception

import com.pawith.commonmodule.exception.Error

class OAuthNotFoundException(error: Error) : OAuthException(error)
