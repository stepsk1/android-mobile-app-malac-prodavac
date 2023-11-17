package com.triforce.malacprodavac.presentation.profile

sealed class ProfileEvent {
    object Logout: ProfileEvent()
    object onAddMediaButtonPress: ProfileEvent()
}