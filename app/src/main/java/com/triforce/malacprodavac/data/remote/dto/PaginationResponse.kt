package com.triforce.malacprodavac.data.remote.dto

import com.squareup.moshi.Json
import com.triforce.malacprodavac.data.local.category.CategoryEntity
import com.triforce.malacprodavac.data.local.product.ProductEntity

data class PaginationResponse (
    @Json(name = "data")
    val data: List<CategoryEntity>,

    @Json(name = "products")
    val products: List<ProductEntity>
)