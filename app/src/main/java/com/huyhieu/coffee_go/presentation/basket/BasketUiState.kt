package com.huyhieu.coffee_go.presentation.basket

import com.huyhieu.domain.entity.Order
import com.huyhieu.libs.toData
import com.huyhieu.libs.toJson

data class BasketUiState(
    val isLoading: Boolean = true,
    val orders: List<Order> = emptyList(),
    val isShowCheckbox: Boolean = false,
    val idsCheckedJson: String = emptyList<String>().toJson(),
) {
    fun idsChecked() = idsCheckedJson.toData<List<Int>>()
}