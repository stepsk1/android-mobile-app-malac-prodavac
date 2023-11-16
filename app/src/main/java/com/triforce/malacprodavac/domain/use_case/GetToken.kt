package com.triforce.malacprodavac.domain.use_case

import com.triforce.malacprodavac.data.services.SessionManager

data class GetToken(val sessionManager: SessionManager) {
    operator fun invoke(): String? {
        return sessionManager.getAccessToken()
    }
}
