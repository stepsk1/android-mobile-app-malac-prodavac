package com.triforce.malacprodavac.data.services

import javax.inject.Inject

class SessionManager @Inject constructor(
    private val pref: AppSharedPreferences
) {
    fun isAuthenticated(): Boolean =
        !getAccessToken().isNullOrEmpty() && getAuthUserId() > 0

    fun getAccessToken(): String? = pref.getToken()


    fun refreshToken(refreshToken: String): String {
        pref.setRefreshToken(refreshToken)
        return refreshToken
    }

    fun getAuthUserId(): Int = pref.getAuthUserId()

    fun refreshAuthUserId(authUserId: Int): Int {
        pref.setAuthUserId(authUserId)
        return authUserId
    }

    fun logout() {
        pref.removeAuthUserId()
        pref.removeToken()
        pref.removeRefreshToken()
    }
}