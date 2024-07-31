package com.huyhieu.coffee_go.screens.basket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.entity.Order
import com.huyhieu.domain.intractor.order.GetAllOrdersUseCase
import com.huyhieu.libs.logDebug
import com.huyhieu.libs.toJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketVM @Inject constructor(
    private val getAllOrdersUseCase: GetAllOrdersUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(BasketUiState())
        private set

    init {
        loadData()
    }
    fun loadData() {
        viewModelScope.launch {
            getAllOrders()
        }
    }


    private fun getAllOrders() {
        viewModelScope.launch {
            getAllOrdersUseCase().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ResultState.Loading()
            ).collectLatest { result ->
                uiState.let {
                    uiState = it.copy(
                        orders = result
                    )
                }
                logDebug(result.toJson(), "getAllOrders")
            }
        }
    }
}