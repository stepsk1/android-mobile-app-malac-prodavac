package com.triforce.malacprodavac.data.repository.products

import android.util.Log
import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.mappers.toProduct
import com.triforce.malacprodavac.data.remote.products.ProductsApi
import com.triforce.malacprodavac.data.remote.products.dto.CreateProductDto
import com.triforce.malacprodavac.data.remote.products.dto.UpdateProductDto
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.model.CreateProduct
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.model.UpdateProduct
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(

    private val api: ProductsApi,
    private val db: MalacProdavacDatabase,
    private val sessionManager: SessionManager

) : ProductRepository {

    private val dao = db.productDao

    override suspend fun getProducts(
        categoryId: Int,
        fetchFromRemote: Boolean,
        queryMap: MutableMap<String, String>
    ): Flow<Resource<List<Product>>> {

        return flow {

            emit(Resource.Loading(isLoading = true))

            val localProducts = dao.getProducts()

            if (localProducts.isNotEmpty()) {
                emit(Resource.Success(data = localProducts.map { it.toProduct() }))
            }


            val isDbEmpty = localProducts.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteProducts = try {
                api.getProducts(queryMap)
            } catch (e: IOException) {

                e.printStackTrace()
                emit(Resource.Error("Couldn't load products"))
                null

            } catch (e: HttpException) {

                e.printStackTrace()
                emit(Resource.Error("Couldn't load products data"))
                null

            }

            remoteProducts?.let {

                Log.d("PRODUCTS:", it.toString())
                emit(Resource.Success(remoteProducts.data.map { jt -> jt.toProduct() }))

            }

            emit(Resource.Loading(false))
        }

    }

    override suspend fun getProduct(id: Int, fetchFromRemote: Boolean): Flow<Resource<Product>> {
        return flow {

            emit(Resource.Loading(isLoading = true))

            val localProducts = dao.getProductForId(id)

            if (localProducts.isNotEmpty()) {
                emit(Resource.Success(data = localProducts.first().toProduct()))
            }


            val isDbEmpty = localProducts.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteProduct = try {
                api.getProduct(id)
            } catch (e: IOException) {

                e.printStackTrace()
                emit(Resource.Error("Couldn't load products"))
                null

            } catch (e: HttpException) {

                e.printStackTrace()
                emit(Resource.Error("Couldn't load products data"))
                null

            }

            remoteProduct?.let {

                Log.d("PRODUCTS:", it.toString())
                emit(Resource.Success(remoteProduct.toProduct()))

            }

            emit(Resource.Loading(false))
        }
    }


    override suspend fun deleteProduct(id: Int): Flow<Resource<Product>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val deletedProduct = try {
                api.delete(id)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't delete Product"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't delete Product"))
                null
            }
            deletedProduct?.let {
                emit(Resource.Success(it.toProduct()))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun insertProduct(createProduct: CreateProduct): Flow<Resource<Product>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val updateProduct = try {
                api.create(createProduct as CreateProductDto)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't create Product"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't create Product"))
                null
            }
            updateProduct?.let {
                emit(Resource.Success(it.toProduct()))
            }

            emit(Resource.Loading(false))
        }
    }

    override suspend fun updateProduct(
        id: Int,
        updateProduct: UpdateProduct
    ): Flow<Resource<Product>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val updatedProduct = try {
                api.update(id, updateProduct as UpdateProductDto)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't update Product"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't update Product"))
                null
            }
            updatedProduct?.let {
                emit(Resource.Success(it.toProduct()))
            }

            emit(Resource.Loading(false))
        }
    }

}