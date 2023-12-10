package com.triforce.malacprodavac.domain.use_case.product

import com.triforce.malacprodavac.domain.repository.products.ProductRepository

class GetProductForId(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int, fetchFromRemote: Boolean) =
        repository.getProduct(productId, fetchFromRemote)
}