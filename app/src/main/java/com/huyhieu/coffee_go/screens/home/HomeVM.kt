package com.huyhieu.coffee_go.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huyhieu.domain.common.ResponseState
import com.huyhieu.domain.intractor.GetAllProductsUseCase
import com.huyhieu.domain.intractor.GetLimitProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    getAllProductsUseCase: GetAllProductsUseCase,
    getLimitProductsUseCase: GetLimitProductsUseCase,
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _homeUiState.value
    )

    val bannerUiState = getLimitProductsUseCase(limit = 5).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ResponseState.Loading()
    )


    init {
        loadHome()
    }


    fun actionNotification() {
        _homeUiState.update {
            it.copy(toolbar = it.toolbar.copy(isBadgeVisible = false))
        }
    }

    private fun loadHome() {
        viewModelScope.launch {
            delay(2000)
            _homeUiState.update {
                it.copy(
                    toolbar = Toolbar(
                        greetings = "Good morning!",
                        name = "Cristiano Ronaldo",
                        isBadgeVisible = true
                    )
                )
            }
        }
    }
}
