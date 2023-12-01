package com.triforce.malacprodavac.data.repository.products.reviews

import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.remote.products.reviews.ReviewsApi
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.model.products.reviews.CreateReviewDto
import com.triforce.malacprodavac.domain.model.products.reviews.Review
import com.triforce.malacprodavac.domain.model.products.reviews.UpdateReviewDto
import com.triforce.malacprodavac.domain.repository.products.reviews.ReviewsRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewsRepositoryImpl @Inject constructor(
    val api: ReviewsApi,
    val db: MalacProdavacDatabase,
    val sessionManager: SessionManager
) : ReviewsRepository {
    override suspend fun createReview(
        productId: Int,
        createReview: CreateReviewDto
    ): Flow<Resource<Review>> {
        return flow {
            emit(Resource.Loading(true))

            val review = try {
                api.createReview(productId, createReview)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                if (e.code() == HttpURLConnection.HTTP_CONFLICT)
                    emit(Resource.Error("Ne možete ostaviti više od jedne recenzije!"))
                else if (e.code() == HttpURLConnection.HTTP_BAD_REQUEST)
                    emit(Resource.Error("Morate popuniti sva polja!"))
                else if (e.code() == HttpURLConnection.HTTP_FORBIDDEN)
                    emit(Resource.Error("Morate kupiti proizvod da biste postavili recenziju!"))
                else
                    emit(Resource.Error("Postavljanje recenzije neuspešno!"))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            }

            review?.let {
                emit(Resource.Success(data = review))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getReviews(productId: Int): Flow<Resource<List<Review>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getReview(productId: Int, reviewId: Int): Flow<Resource<Review>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateReview(
        productId: Int,
        reviewId: Int,
        updateReview: UpdateReviewDto
    ): Flow<Resource<Review>> {
        TODO("Not yet implemented")
    }

}