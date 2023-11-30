package com.triforce.malacprodavac.presentation.profile.profilePublic

sealed class ProfilePublicEvent {
    object favoriteShop: ProfilePublicEvent()
    object removeFavoriteShop: ProfilePublicEvent()
}