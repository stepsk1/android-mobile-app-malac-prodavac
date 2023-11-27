package com.triforce.malacprodavac.domain.use_case.product

import com.triforce.malacprodavac.domain.use_case.order.UpdateOrder

data class ProductUseCase(
    val getAllProducts: GetAllProducts,
    val getProductForId: GetProductForId,
    val updateOrder: UpdateOrder,
    val addProductImages: AddProductImages
)
