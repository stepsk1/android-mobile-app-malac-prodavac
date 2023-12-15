package com.triforce.malacprodavac.domain.use_case.product

import com.triforce.malacprodavac.domain.model.products.productMedias.ProductMedia
import com.triforce.malacprodavac.domain.repository.products.produtMedias.ProductMediasRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

class SetProductImage(private val productMediasRepository: ProductMediasRepository) {
    suspend operator fun invoke(productId: Int, image: File): Flow<Resource<ProductMedia>> {
        return productMediasRepository.createProductImage(productId, image)
    }
}