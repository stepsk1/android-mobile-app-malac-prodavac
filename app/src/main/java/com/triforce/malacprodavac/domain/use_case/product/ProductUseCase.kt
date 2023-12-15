package com.triforce.malacprodavac.domain.use_case.product

data class ProductUseCase(
    val addProduct: AddProduct,
    val getAllProducts: GetAllProducts,
    val getProductForId: GetProductForId,
    val updateProduct: UpdateProduct,
    val setProductImage: SetProductImage
)
