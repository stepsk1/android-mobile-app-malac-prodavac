package com.triforce.malacprodavac.presentation.myProducts

import com.triforce.malacprodavac.presentation.profile.profilePrivate.ProfilePrivateEvent

sealed class MyProductsEvent {
    object Refresh : MyProductsEvent()

}