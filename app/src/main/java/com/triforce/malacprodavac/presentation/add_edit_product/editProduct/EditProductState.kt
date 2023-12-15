package com.triforce.malacprodavac.presentation.add_edit_product.editProduct

import android.net.Uri
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.model.products.Product

data class EditProductState(

    val productId: Int? = -1,
    val product: Product? = null,
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val isUpdateSuccessful: Boolean = false,

    val thumbUrl: String? = null,

    val errorMessage: String? = null,
    val titleError: String? = null,
    val priceError: String? = null,

    val imageUris: List<Uri> = emptyList()
)