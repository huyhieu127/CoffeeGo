package com.huyhieu.coffee_go.presentation.basket

import com.huyhieu.domain.entity.Order

sealed class BasketAction {
    data object CloseClick : BasketAction()
    data class ShowCheckboxClick(val isShown: Boolean) : BasketAction()
    data class ItemClick(val order: Order) : BasketAction()
    data class DeleteClick(val orderIds: List<Int>) : BasketAction()
    data class ChangeCheckbox(val isChecked: Boolean, val orderId: Int) : BasketAction()
}