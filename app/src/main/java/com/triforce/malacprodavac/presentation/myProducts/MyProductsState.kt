package com.triforce.malacprodavac.presentation.myProducts

import android.net.Uri
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.model.Shop
import com.triforce.malacprodavac.domain.model.User

data class MyProductsState (
    val id: Int = -1,

    val searchQuery: String = "",

    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = true,
    val currentUser: User? = null,
    val currentShop: Shop? = null,
    val profileImageUrl: String? = null,
    val profileImageKey: String? = null,
    val token: String? = null,
    var mediaUri: Uri? = null,
    var newImage: Boolean = false,

    val products: List<Product>? = emptyList(),
)