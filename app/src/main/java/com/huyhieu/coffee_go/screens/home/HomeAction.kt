package com.huyhieu.coffee_go.screens.home

import com.huyhieu.domain.entity.Coffee

sealed interface HomeAction {
    data object AvatarClick : HomeAction
    data object NotificationClick : HomeAction
    data class BannerClick(val banner: Banner) : HomeAction

    data object NearbyShopViewAllClick : HomeAction
    data class NearbyShopItemClick(val coffee: Coffee) : HomeAction

    data object PopularMenuViewAllClick : HomeAction
    data class PopularMenuItemClick(val coffee: Coffee) : HomeAction

    data object CheckBasketClick : HomeAction
}
