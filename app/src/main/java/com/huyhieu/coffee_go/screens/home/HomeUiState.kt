package com.huyhieu.coffee_go.screens.home

import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.entity.Order

data class HomeUiState(
    val toolbar: Toolbar = Toolbar(),
    val banner: ResultState<List<Coffee>> = ResultState.Loading(),
    val nearbyShop: ResultState<List<Coffee>> = ResultState.Loading(),
    val popularMenu: ResultState<List<Coffee>> = ResultState.Loading(),
    val basket: ResultState<List<Order>?> = ResultState.Loading(),
    val isHaveBasket: Boolean = false,
)

data class Toolbar(
    var greetings: String = "",
    var name: String = "",
    var urlAvatar: String = "",
    var isBadgeVisible: Boolean = false
)

data class Banner(
    val bannerUrl: String = "",
    val bannerRes: Int = 0,
    val id: String = "",
    val type: String = "",
)