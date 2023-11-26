package com.triforce.malacprodavac.presentation.profile.profilePrivate

import android.content.Context
import android.net.Uri

sealed class ProfilePrivateEvent {
    object Logout : ProfilePrivateEvent()
    object Refresh : ProfilePrivateEvent()
    data class ChangeProfilePicture(val uri: Uri, val context: Context) : ProfilePrivateEvent()
}