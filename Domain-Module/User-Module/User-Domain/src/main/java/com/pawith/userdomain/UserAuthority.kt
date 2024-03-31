package com.pawith.userdomain

class UserAuthority(
    val authority: Authority,
) {
    companion object{
        fun createUserAuthority() : UserAuthority{
            return UserAuthority(Authority.USER)
        }

        fun createGuestAuthority() : UserAuthority{
            return UserAuthority(Authority.GUEST)
        }
    }

    fun isGuest():Boolean{
        return authority == Authority.GUEST
    }

}