package com.huyhieu.coffee_go.presentation.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.intractor.coffee.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreVM @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(StoreUiState())
        private set

    init {
        getNearbyShopUiState()
    }

    fun onAction(action: StoreAction) {
        when (action) {
            is StoreAction.TabSelected -> {
                uiState.let {
                    uiState = it.copy(tabSelected = action.tab)
                }
            }
            else -> Unit
        }
    }


    private fun getNearbyShopUiState() {
        viewModelScope.launch {
            getAllProductsUseCase().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ResultState.Loading()
            ).collectLatest { result ->
                uiState.let {
                    uiState = it.copy(
                        listForYou = result
                    )
                }
            }
        }
    }
}