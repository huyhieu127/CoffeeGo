package com.huyhieu.coffee_go.screens.order_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.entity.Order
import com.huyhieu.domain.entity.OrderOption
import com.huyhieu.domain.entity.OrderOptional
import com.huyhieu.domain.intractor.order.InsertOrderUseCase
import com.huyhieu.libs.logDebug
import com.huyhieu.libs.toJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class OrderDetailVM @Inject constructor(
    private val insertOrderUseCase: InsertOrderUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(OrderDetailUiState())
        private set

    fun onAction(action: OrderDetailAction) {
        when (action) {
            OrderDetailAction.FavoriteClick -> onFavoriteClick()
            OrderDetailAction.ShareClick -> onShareClick()
            OrderDetailAction.MinusQuantityClick -> onMinusQuantityClick()
            OrderDetailAction.PlusQuantityClick -> onPlusQuantityClick()
            is OrderDetailAction.AvailableInClick -> onAvailableInClick(action.orderOption)
            is OrderDetailAction.SizeClick -> onSizeClick(action.orderOption)
            is OrderDetailAction.ItemSelectedFlavorProfile -> onItemSelectedFlavorProfile(action.orderOptional)
            OrderDetailAction.ExpandFlavorProfileClick -> onExpandFlavorProfileClick()
            is OrderDetailAction.ItemSelectedGrindOption -> onItemSelectedGrindOption(action.orderOptional)
            OrderDetailAction.ExpandGrindOptionClick -> onExpandGrindOptionClick()
            else -> Unit
        }
    }

    fun onAddToBasketClick(onAction: () -> Unit) {
        viewModelScope.launch {
            uiState.also {
                val order = Order(
                    coffeeId = it.coffee?.id ?: -1,
                    quantity = it.quantity,
                    availableInId = it.availableInSelected?.id,
                    sizeId = it.sizeSelected?.id,
                    flavorProfileId = it.flavorProfileSelected?.id,
                    grindOptionId = it.grindOptionSelected?.id,
                    totalPrice = it.totalPrice,
                )
                insertOrderUseCase(order).collectLatest { resultState ->
                    logDebug(resultState.toJson())
                    if (resultState is ResultState.Success) {
                        logDebug(resultState.data.toString())
                        onAction()
                    }
                }
            }
        }
    }

    private fun onExpandGrindOptionClick() {
        uiState.isExpandGrindOption.let {
            uiState = uiState.copy(isExpandGrindOption = it.not())
        }
    }

    private fun onItemSelectedGrindOption(orderOptional: OrderOptional) {
        uiState.let {
            uiState = it.copy(grindOptionSelected = orderOptional)
        }
    }

    private fun onExpandFlavorProfileClick() {
        uiState.isExpandFlavorProfile.let {
            uiState = uiState.copy(isExpandFlavorProfile = it.not())
        }
    }

    private fun onItemSelectedFlavorProfile(orderOptional: OrderOptional) {
        uiState.let {
            uiState = it.copy(flavorProfileSelected = orderOptional)
        }
    }

    private fun onSizeClick(orderOption: OrderOption) {
        uiState.let {
            uiState = it.copy(sizeSelected = orderOption)
        }
    }

    private fun onAvailableInClick(orderOption: OrderOption) {
        uiState.let {
            uiState = it.copy(availableInSelected = orderOption)
        }
    }

    private fun onPlusQuantityClick() {
        uiState.quantity.let {
            if (it < 5) {
                uiState = uiState.copy(quantity = it + 1)
            }
        }
    }

    private fun onMinusQuantityClick() {
        uiState.quantity.let {
            if (it > 1) {
                uiState = uiState.copy(quantity = it - 1)
            }
        }
    }

    private fun onShareClick() {

    }

    private fun onFavoriteClick() {
        uiState.isFavorite.let {
            uiState = uiState.copy(isFavorite = it.not())
        }
    }

    suspend fun loadData(orderId: Int, coffee: Coffee?) = supervisorScope {
        if (coffee != null) {
            loadOrder(orderId).collect { orderState ->
                if (orderState is OrderState.Success) {
                    uiState.let {
                        val order = orderState.order
                        if (order != null) {
                            uiState = it.copy(
                                coffee = coffee,
                                isFavorite = coffee.isFavorite,
                                quantity = order.quantity,
                                availableInSelected = order.getAvailableIn(coffee),
                                sizeSelected = order.getSize(coffee),
                                flavorProfileSelected = order.getFlavorProfile(coffee),
                                grindOptionSelected = order.getGrindOption(coffee),
                            )
                        } else {
                            uiState = it.copy(
                                coffee = coffee,
                                isFavorite = coffee.isFavorite,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadOrder(orderId: Int) = flow {
        if (orderId == -1) {
            emit(OrderState.Success(null))
        }
        delay(500)
        emit(
            OrderState.Success(
                Order(
                    quantity = 3,
                    availableInId = 1,
                    sizeId = 2,
                    flavorProfileId = 1,
                    grindOptionId = 0,
                )
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = OrderState.Loading,
    )
}