package com.triforce.malacprodavac.domain.use_case.product

import com.triforce.malacprodavac.domain.model.products.CreateProductDto
import com.triforce.malacprodavac.domain.repository.products.ProductRepository

class AddProduct(private val repository: ProductRepository) {
    suspend operator fun invoke(createProduct: CreateProductDto) =
        repository.insertProduct(createProduct)
}