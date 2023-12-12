package com.triforce.malacprodavac.presentation.cart.CartDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.remote.orders.dto.CreateOrderDto
import com.triforce.malacprodavac.data.remote.orders.dto.CreateSchedulePickupDto
import com.triforce.malacprodavac.data.repository.cart.CartRepository
import com.triforce.malacprodavac.domain.repository.OrderRepository
import com.triforce.malacprodavac.domain.repository.ScheduledPickupRepository
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.cart.components.BoughtProducts
import com.triforce.malacprodavac.presentation.cart.components.CartItem
import com.triforce.malacprodavac.presentation.cart.scheduling.ScheduleState
import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartDetailsViewModel @Inject constructor(
    private val repository: OrderRepository,
    private val repositorySchedule: ScheduledPickupRepository
) : ViewModel() {


    var state by mutableStateOf(CartDetailsState())
    var stateSchedule by mutableStateOf(ScheduleState())

    fun onEvent(event: CartDetailsEvent) {
        when (event) {
            CartDetailsEvent.order -> {
                for (boughtProduct in BoughtProducts.listOfBoughtProducts) {
                    buyProducts(
                        boughtProduct.product.id,
                        boughtProduct.amount,
                        BoughtProducts.deliveryMethod,
                        BoughtProducts.paymentMethod,
                        BoughtProducts.localDate,
                        BoughtProducts.localTime
                    )
                }

                BoughtProducts.listOfBoughtProducts.removeAll(BoughtProducts.listOfBoughtProducts)
            }
        }
    }

    private fun buyProducts(
        productId: Int,
        quantity: Int,
        deliveryMethod: DeliveryMethod,
        paymentMethod: PaymentMethod,
        localDate: String,
        localTime: String
    ) {
        viewModelScope.launch {
            repository.insertOrder(
                createOrder = CreateOrderDto(
                    deliveryMethod = deliveryMethod,
                    paymentMethod = paymentMethod,
                    productId = productId,
                    quantity = quantity
                )
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(isSuccesful = true)
                            state = state.copy(orderId = result.data.id)
                            if (deliveryMethod == DeliveryMethod.SelfPickup) {
                                scheduleProducts(state.orderId, localDate, localTime)
                            }
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

    private fun scheduleProducts(
        orderId: Int,
        date: String,
        time: String
    ) {
        viewModelScope.launch {
            repositorySchedule.insertScheduledPickup(
                id = orderId,
                createSchedulePickup = CreateSchedulePickupDto(
                    date = date,
                    timeOfDay = time
                )
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            stateSchedule = stateSchedule.copy(isSuccesful = true)
                        }
                    }

                    is Resource.Error -> {
                        Unit
                    }

                    is Resource.Loading -> {
                        stateSchedule = stateSchedule.copy(
                            isLoading = result.isLoading
                        )
                    }
                }
            }
        }
    }
}