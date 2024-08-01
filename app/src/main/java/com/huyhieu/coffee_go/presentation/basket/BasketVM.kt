package com.huyhieu.coffee_go.presentation.basket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.intractor.order.DeleteOrderByIdsUseCase
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
    private val deleteOrderByIdsUseCase: DeleteOrderByIdsUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(BasketUiState())
        private set

    init {
        loadData()
    }

    fun onAction(action: BasketAction) {
        when (action) {
            is BasketAction.ShowCheckboxClick -> {
                uiState = uiState.copy(
                    isShowCheckbox = action.isShown,
                    idsCheckedJson = emptyList<String>().toJson()
                )
            }

            is BasketAction.DeleteClick -> {
                deleteOrder(orderIds = action.orderIds)
                onAction(BasketAction.ShowCheckboxClick(false))
            }

            is BasketAction.ChangeCheckbox -> {
                val idsChecked = uiState.idsChecked()?.toMutableList() ?: mutableListOf()
                if (action.isChecked) {
                    idsChecked.add(action.orderId)
                } else {
                    idsChecked.remove(action.orderId)
                }
                val idsCheckedJson = idsChecked.toJson()
                uiState = uiState.copy(
                    idsCheckedJson = idsCheckedJson
                )
            }

            else -> Unit
        }
    }

    private fun loadData() {
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
                        isLoading = result is ResultState.Loading,
                        orders = if (result is ResultState.Success) result.data.orEmpty() else emptyList(),
                    )
                }
                logDebug(result.toJson(), "getAllOrders")
            }
        }
    }

    private fun deleteOrder(orderIds: List<Int>) {
        viewModelScope.launch {
            deleteOrderByIdsUseCase(*orderIds.toIntArray())
                .collectLatest { result ->
                    logDebug(result.toJson(), "deleteOrder")
                }
        }
    }
}