package com.huyhieu.coffee_go.presentation.order_detail

import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.entity.Order
import com.huyhieu.domain.entity.OrderOption
import com.huyhieu.domain.entity.OrderOptional

data class OrderDetailUiState(
    val coffee: Coffee? = null,
    val isFavorite: Boolean = false,

    val orderId: Int = -1,
    val quantity: Int = 1,

    val availableInSelected: OrderOption? = null,
    val sizeSelected: OrderOption? = null,

    val flavorProfileSelected: OrderOptional? = null,
    val isExpandFlavorProfile: Boolean = true,

    val grindOptionSelected: OrderOptional? = null,
    val isExpandGrindOption: Boolean = true,
) {

    val totalPrice
        get() = ((coffee?.price ?: 0.0) * quantity)
            .plus(sizeSelected?.extraPrice ?: 0.0)
            .plus(flavorProfileSelected?.extraPrice ?: 0.0)
            .plus(grindOptionSelected?.extraPrice ?: 0.0)
}

sealed interface OrderState {
    data object Loading: OrderState
    data class Success(val order: Order?) : OrderState
}