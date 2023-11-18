package com.triforce.malacprodavac.presentation.profile.profilePrivate

import android.net.Uri
import com.triforce.malacprodavac.domain.model.User

data class ProfilePrivateState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = true,
    val currentUser: User? = null,
    val token: String? = null,
    var mediaUris: List<Uri> = emptyList(),
    var newImage: Boolean = false
)