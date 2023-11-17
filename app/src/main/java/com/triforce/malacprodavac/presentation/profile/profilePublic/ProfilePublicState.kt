package com.triforce.malacprodavac.presentation.profile.profilePublic

import com.triforce.malacprodavac.domain.model.User

data class ProfilePublicState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = true,
    val currentUser: User? = null,
    val token: String? = null
)