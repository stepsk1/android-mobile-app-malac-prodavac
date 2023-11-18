package com.triforce.malacprodavac.presentation.profile.profilePrivate

sealed class ProfilePrivateEvent {
    object Logout: ProfilePrivateEvent()
    object onAddMediaButtonPress: ProfilePrivateEvent()
}