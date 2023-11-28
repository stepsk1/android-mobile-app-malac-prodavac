package com.triforce.malacprodavac.presentation.profile.profilePublic

import android.net.Uri
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.model.Shop
import com.triforce.malacprodavac.domain.model.User

data class ProfilePublicState(
    val isLoading: Boolean = false,
    val currentUser: User? = null,
    val currentShop: Shop? = null,
    val products: List<Product>? = emptyList(),

    val profileImageUrl: String? = null,
    val profileImageKey: String? = null,
    val token: String? = null,
    var mediaUri: Uri? = null,
    var newImage: Boolean = false,

    val isFavorite: Boolean = false
)