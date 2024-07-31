package com.huyhieu.coffee_go.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.intractor.coffee.GetAllProductsUseCase
import com.huyhieu.domain.intractor.coffee.GetLimitProductsUseCase
import com.huyhieu.domain.intractor.coffee.GetSortProductsUseCase
import com.huyhieu.domain.intractor.order.GetAllOrdersUseCase
import com.huyhieu.domain.utils.TypeSort
import com.huyhieu.libs.logDebug
import com.huyhieu.libs.toJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getLimitProductsUseCase: GetLimitProductsUseCase,
    private val getSortProductsUseCase: GetSortProductsUseCase,
    private val getAllOrdersUseCase: GetAllOrdersUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        loadHome()
    }

    fun onAction(action: HomeAction) {

    }


    fun actionNotification(onAction: () -> Unit) {
        uiState.also {
            uiState = it.copy(
                toolbar = it.toolbar.copy(isBadgeVisible = false),
            )
            onAction()
        }
    }

    private fun loadHome() {
        viewModelScope.launch {
            delay(500)
            uiState.let {
                uiState = it.copy(
                    toolbar = Toolbar(
                        greetings = "Good morning!",
                        name = "Cristiano Ronaldo",
                        isBadgeVisible = true
                    )
                )
            }
            getAllOrders()
            getBannerState()
            getNearbyShopUiState()
            getPopularMenuUiState()
        }
    }


    private fun getBannerState() {
        viewModelScope.launch {
            getLimitProductsUseCase(limit = 5)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = ResultState.Loading()
                ).collectLatest { result ->
                    uiState.let {
                        uiState = it.copy(
                            banner = result
                        )
                    }
                }
        }
    }

    private fun getNearbyShopUiState() {
        viewModelScope.launch {
            getSortProductsUseCase(typeSort = TypeSort.DESCENDING).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ResultState.Loading()
            ).collectLatest { result ->
                uiState.let {
                    uiState = it.copy(
                        nearbyShop = result
                    )
                }
            }
        }
    }

    private fun getPopularMenuUiState() {
        viewModelScope.launch {
            getSortProductsUseCase(typeSort = TypeSort.ASCENDING).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ResultState.Loading()
            ).collectLatest { result ->
                uiState.let {
                    uiState = it.copy(
                        popularMenu = result
                    )
                }
            }
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
                    uiState = when (result) {
                        is ResultState.Success -> {
                            it.copy(
                                basket = result,
                                isHaveBasket = result.data?.isNotEmpty() ?: false
                            )
                        }

                        else -> {
                            it.copy(
                                basket = result,
                            )
                        }
                    }
                }
                logDebug(result.toJson(), "getAllOrders")
            }
        }
    }
}
