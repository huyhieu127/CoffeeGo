package com.huyhieu.coffee_go.screens.basket

import com.huyhieu.domain.entity.Order

sealed class BasketAction {
    data object CloseClick : BasketAction()
    data class ItemClick(val order: Order) : BasketAction()
}