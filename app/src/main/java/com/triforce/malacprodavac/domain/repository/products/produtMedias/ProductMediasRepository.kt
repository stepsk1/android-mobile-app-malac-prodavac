package com.triforce.malacprodavac.domain.repository.products.produtMedias

import com.triforce.malacprodavac.domain.model.products.productMedias.ProductMedia
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ProductMediasRepository {
    suspend fun createProductImages(productId: Int, images: List<File>): Flow<Resource<Int>>
    suspend fun getProductImages(productId: Int): Flow<Resource<List<ProductMedia>>>

    suspend fun deleteProductImage(productId: Int, mediaId: Int): Flow<Resource<ProductMedia>>
}