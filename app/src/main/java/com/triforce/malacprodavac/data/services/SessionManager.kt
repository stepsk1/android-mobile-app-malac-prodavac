package com.triforce.malacprodavac.data.services


import io.github.nefilim.kjwt.ClaimsVerification
import io.github.nefilim.kjwt.JWSHMAC256Algorithm
import io.github.nefilim.kjwt.verify
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val pref: AppSharedPreferences
) {

    fun isAuthenticated(): Boolean {
        val tokenOrNull = getAccessToken() ?: return false
        val claim =
            verify<JWSHMAC256Algorithm>(tokenOrNull.substringAfter("malac_prodavac-auth_tkn="),
                "a6a7092c8f48eec50d424cb2bcdfe4e781f0bb9aaa1d486fa1cd1c77e99eed27f0f72bf6bfbbe1b223b55e3f849514701c63eafa4d745fe6c51e622ec4162c7b2ebb53f347309ddbeb3826ca00905f860f9db93f17fda5eccb8972715ea031ad817b5c2763b85dabebcb325ec155cd758c5f98fac48132d59f65a4ac857ae90d97d0100d91b4f5e1d11708b2506da91fd267a6b30cdb850b9d2576306a847143",
                ClaimsVerification.requiredOptionClaim(
                    "expiresAt",
                    { expiresAt() },
                    {
                        it >= Instant.now()
                            .plus(1, ChronoUnit.MINUTES)
                    }
                ))

        return claim.isValid
    }

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