package com.triforce.malacprodavac.domain.use_case.profile

import com.triforce.malacprodavac.domain.use_case.GetToken
import com.triforce.malacprodavac.domain.use_case.auth.IsAuthenticated
import com.triforce.malacprodavac.domain.use_case.login.Me

class Profile(
    val getMe: Me,
    val logout: Logout,
    val getToken: GetToken,
    val setProfilePicture: SetProfilePicture,
    val isAuthenticated: IsAuthenticated
)