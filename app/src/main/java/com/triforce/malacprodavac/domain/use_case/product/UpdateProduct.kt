package com.triforce.malacprodavac.domain.use_case.product

import com.triforce.malacprodavac.domain.model.products.UpdateProductDto
import com.triforce.malacprodavac.domain.repository.products.ProductRepository

data class UpdateProduct(val repository: ProductRepository) {
    suspend operator fun invoke(productId: Int, updateProductDto: UpdateProductDto) =
        repository.updateProduct(productId, updateProductDto)
}
