package com.triforce.malacprodavac.domain.use_case.product

import com.triforce.malacprodavac.domain.repository.products.produtMedias.ProductMediasRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

class AddProductImages(private val productMediasRepository: ProductMediasRepository) {
    suspend operator fun invoke(productId: Int, images: List<File>): Flow<Resource<Int>> {
        return productMediasRepository.createProductImages(productId, images)
    }
}