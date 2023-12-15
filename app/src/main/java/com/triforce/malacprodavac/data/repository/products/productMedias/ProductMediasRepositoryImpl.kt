package com.triforce.malacprodavac.data.repository.products.productMedias

import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.remote.products.productMedias.ProductMediasApi
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.model.products.productMedias.ProductMedia
import com.triforce.malacprodavac.domain.repository.products.produtMedias.ProductMediasRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductMediasRepositoryImpl @Inject constructor(
    private val api: ProductMediasApi,
    private val db: MalacProdavacDatabase,
    private val sessionManager: SessionManager
) : ProductMediasRepository {
    override suspend fun createProductImage(
        productId: Int,
        image: File
    ): Flow<Resource<ProductMedia>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val part =
                MultipartBody.Part.createFormData(
                    "image",
                    image.name,
                    image.asRequestBody("image/jpeg".toMediaType())
                )


            val productMedia = try {
                api.create(
                    productId,
                    part
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            }

            productMedia?.let {
                emit(Resource.Success(data = it))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun getProductImages(productId: Int): Flow<Resource<List<ProductMedia>>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProductImage(
        productId: Int,
        mediaId: Int
    ): Flow<Resource<ProductMedia>> {
        TODO("Not yet implemented")
    }

}