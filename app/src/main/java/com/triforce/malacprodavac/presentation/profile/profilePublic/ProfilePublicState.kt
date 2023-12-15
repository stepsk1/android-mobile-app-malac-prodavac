package com.triforce.malacprodavac.presentation.profile.profilePublic

import android.net.Uri
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.model.shops.Shop

data class ProfilePublicState(
    val isLoading: Boolean = false,

    val role: Int = -1,

    val user: User? = null,
    val shop: Shop? = null,
    val products: List<Product>? = emptyList(),

    val profileImageUrl: String? = null,
    val profileImageKey: String? = null,

    val token: String? = null,

    var mediaUri: Uri? = null,
    var newImage: Boolean = false,
)