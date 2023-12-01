package com.triforce.malacprodavac.presentation.product

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.products.reviews.CreateReviewDto
import com.triforce.malacprodavac.domain.repository.products.ProductRepository
import com.triforce.malacprodavac.domain.use_case.product.replies.ReviewUseCase
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val reviewUseCase: ReviewUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(ProductState())

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.buyProduct -> {
                state = state.copy(isBuyed = true)
            }

            is ProductEvent.ToggleFavouriteProduct -> {
                state = state.copy(isBuyed = true)
            }

            is ProductEvent.favoriteProduct -> {
                state = state.copy(isFavorite = true)
            }

            is ProductEvent.removeFavoriteProduct -> {
                state = state.copy(isFavorite = false)
            }

            is ProductEvent.CreateReview -> {
                createReview(event.text, event.rating)
            }
        }
    }


    init {
        savedStateHandle.get<Int>("productId")?.let { productId ->
            getProduct(true, productId)
            getReviews(productId)
            FavouriteProduct.favouriteProductId = productId
            FavouriteProduct.favouriteProduct = state.product
            FavouriteProduct.favProducts.add(state.product)
        }
    }

    private fun getReviews(productId: Int) {
        viewModelScope.launch {
            reviewUseCase.getReviews(productId).collect { result ->
                when (result) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        state = state.copy(reviews = result.data)
                    }
                }
            }
        }
    }

    private fun createReview(text: String, rating: Int) {
        viewModelScope.launch {
            reviewUseCase.createReview.invoke(state.product!!.id, CreateReviewDto(text, rating))
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            state = state.copy(createReviewError = result.message)
                        }

                        is Resource.Loading -> {
                            state = state.copy()
                        }

                        is Resource.Success -> {
                            result.data?.let {
                                state = state.copy(
                                    reviews = listOf(*state.reviews!!.toTypedArray(), it)
                                )
                            }
                        }
                    }
                }


        }
    }

    private fun getProduct(fetchFromRemote: Boolean, productId: Int) {

        viewModelScope.launch {
            repository.getProduct(productId, fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(product = it)
                        }
                    }

                    is Resource.Error -> {
                        Unit
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading
                        )
                    }
                }
            }
        }
    }

}