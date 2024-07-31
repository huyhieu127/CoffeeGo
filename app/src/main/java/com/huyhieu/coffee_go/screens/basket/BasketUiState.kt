package com.huyhieu.coffee_go.screens.basket

import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.entity.Order

data class BasketUiState(
    val orders: ResultState<List<Order>?> = ResultState.Loading(),
)