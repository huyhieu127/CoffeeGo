package com.huyhieu.coffee_go.screens.order_detail

import com.huyhieu.domain.entity.OrderOption
import com.huyhieu.domain.entity.OrderOptional

sealed interface OrderDetailAction {
    data object CloseClick : OrderDetailAction
    data object FavoriteClick : OrderDetailAction
    data object ShareClick : OrderDetailAction

    data object AddToBasketClick : OrderDetailAction

    data object PlusQuantityClick : OrderDetailAction
    data object MinusQuantityClick : OrderDetailAction

    data class AvailableInClick(val orderOption: OrderOption) : OrderDetailAction
    data class SizeClick(val orderOption: OrderOption) : OrderDetailAction

    data object ExpandFlavorProfileClick : OrderDetailAction
    data class ItemSelectedFlavorProfile(val orderOptional: OrderOptional) : OrderDetailAction

    data object ExpandGrindOptionClick : OrderDetailAction
    data class ItemSelectedGrindOption(val orderOptional: OrderOptional) : OrderDetailAction
}