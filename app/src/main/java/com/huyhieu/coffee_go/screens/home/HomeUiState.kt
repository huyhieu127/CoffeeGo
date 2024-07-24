package com.huyhieu.coffee_go.screens.home


data class HomeUiState(
    val toolbar: Toolbar = Toolbar(),
    val banners: List<Banner> = emptyList(),
    val coffeeShops: List<CoffeeShop> = emptyList(),
    val coffees: List<Coffee> = emptyList(),
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

data class CoffeeShop(
    val url: String = "",
    val id: String = "",
    val name: String = "",
    val lat: Double = 0.0,
    val long: Double = 0.0,
    val rating: Double = 0.0,
)

data class Coffee(
    val url: String = "",
    val id: String = "",
    val type: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val rating: Double = 0.0
)