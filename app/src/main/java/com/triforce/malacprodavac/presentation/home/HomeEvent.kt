package com.triforce.malacprodavac.presentation.home

import com.triforce.malacprodavac.presentation.profile.profilePrivate.ProfilePrivateEvent

sealed class HomeEvent {
    object Refresh : HomeEvent()
}
