package com.triforce.malacprodavac.data.repository.products

import android.util.Log
import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.mappers.toCategory
import com.triforce.malacprodavac.data.mappers.toProduct
import com.triforce.malacprodavac.data.remote.products.ProductsApi
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.CategoryRepository
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

) :ProductRepository {

    private val dao = db.productDao

    override suspend fun getProducts(fetchFromRemote: Boolean): Flow<Resource<List<Product>>> {

        return flow {

            emit(Resource.Loading(isLoading = true))

            val localProducts = dao.getProducts()

            emit(Resource.Success(data = localProducts.map { it.toProduct() }))

            val isDbEmpty = localProducts.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteProducts = try {

                api.getProducts()

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
                emit(Resource.Success(remoteProducts.products.map { it.toProduct() }))

            }

            emit(Resource.Loading(false))
        }

    }

    override suspend fun getProductForId(
        id: Int,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Product>>> {

        return flow {

            emit(Resource.Loading(isLoading = true))

            val localProduct = dao.getProductForId(id)

            emit(Resource.Success(data = localProduct.map { it.toProduct() }))

            val isDbEmpty = localProduct.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteProduct = try {

                api.getProductForId(id)

            } catch (e: IOException) {

                e.printStackTrace()
                emit(Resource.Error("Couldn't load product"))
                null

            } catch (e: HttpException) {

                e.printStackTrace()
                emit(Resource.Error("Couldn't load product data"))
                null

            }

            remoteProduct?.let {

                Log.d("PRODUCTS:", it.toString())
                emit(Resource.Success(remoteProduct.products.map { it.toProduct() }))

            }

            emit(Resource.Loading(false))
        }

    }

    override suspend fun getProductsForCategoryId(
        id: Int,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Product>>> {
        TODO("Not yet implemented")
    }

}