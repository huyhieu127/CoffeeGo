package com.huyhieu.coffee_go.presentation.store

import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.entity.Coffee

data class StoreUiState(
    val tabSelected: Int = 0,
    val listForYou: ResultState<List<Coffee>> = ResultState.Loading(),
)