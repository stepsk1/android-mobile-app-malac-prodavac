package com.triforce.malacprodavac.data.services

import javax.inject.Inject

class SessionManager @Inject constructor(
    private val pref: AppSharedPreferences
) {
    fun getAccessToken(): String? = pref.getToken()


    fun refreshToken(refreshToken: String): String {
        pref.setRefreshToken(refreshToken)
        return refreshToken
    }


    fun logout() {
        pref.removeToken()
        pref.removeRefreshToken()
    }
}