package com.huyhieu.coffee_go.screens.basket

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.huyhieu.coffee_go.navigation.route.MainDest

fun NavController.navigateToBasket() {
    this.navigate(MainDest.Basket) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.basket(
    onCloseScreen: () -> Unit = {},
) {
    composable<MainDest.Basket> {
        BasketScreen(
            onCloseScreen = onCloseScreen,
        )
    }
}