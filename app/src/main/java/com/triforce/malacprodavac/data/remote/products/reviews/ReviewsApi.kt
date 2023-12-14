package com.triforce.malacprodavac.data.remote.products.reviews

import com.triforce.malacprodavac.data.remote.products.ProductsApi
import com.triforce.malacprodavac.domain.model.pagination.PaginationResult
import com.triforce.malacprodavac.domain.model.products.reviews.CreateReviewDto
import com.triforce.malacprodavac.domain.model.products.reviews.Review
import com.triforce.malacprodavac.domain.model.products.reviews.UpdateReviewDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewsApi {
    @POST(ROUTE)
    suspend fun createReview(
        @Path("productId") productId: Int,
        @Body createReviewDto: CreateReviewDto
    ): Review

    @GET(ROUTE)
    suspend fun getReviews(@Path("productId") productId: Int): PaginationResult<Review>

    @GET("${ROUTE}/{reviewId}")
    suspend fun getReview(
        @Path("productId") productId: Int,
        @Path("reviewId") reviewId: Int
    ): Review

    @PATCH("${ROUTE}/{reviewId}")
    suspend fun updateReview(
        @Path("productId") productId: Int,
        @Path("reviewId") reviewId: Int, @Body updateReviewDto: UpdateReviewDto
    ): Review

    companion object {
        const val ROUTE = "${ProductsApi.ROUTE}/{productId}/reviews"
    }
}