package com.triforce.malacprodavac.domain.use_case.auth

import com.triforce.malacprodavac.data.services.SessionManager

data class IsAuthenticated(val sessionManager: SessionManager) {
    operator fun invoke() =
        sessionManager.isAuthenticated()
}