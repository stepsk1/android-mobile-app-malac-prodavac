package com.triforce.malacprodavac.data.repository.products

import android.util.Log
import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.mappers.toCategory
import com.triforce.malacprodavac.data.mappers.toProduct
import com.triforce.malacprodavac.data.remote.products.ProductsApi
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.data.services.filter.Filter
import com.triforce.malacprodavac.data.services.filter.FilterBuilder
import com.triforce.malacprodavac.data.services.filter.FilterOperation
import com.triforce.malacprodavac.data.services.filter.SingleFilter
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.CategoryRepository
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.http.QueryMap
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

            if(localProducts.isNotEmpty()){
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


    override suspend fun deleteProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun insertProduct(product: Product) {
        TODO("Not yet implemented")
    }

}