package com.huyhieu.coffee_go.presentation.basket

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.huyhieu.coffee_go.navigation.route.MainDest
import com.huyhieu.domain.entity.Order

fun NavController.navigateToBasket() {
    this.navigate(MainDest.Basket) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.basket(
    onCloseScreen: () -> Unit = {},
    onItemClick: (order: Order) -> Unit = {},
) {
    composable<MainDest.Basket> {
        BasketScreen(
            onCloseScreen = onCloseScreen,
            onItemClick = onItemClick,
        )
    }
}