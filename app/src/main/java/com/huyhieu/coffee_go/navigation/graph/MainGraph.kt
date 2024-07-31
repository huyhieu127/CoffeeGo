package com.huyhieu.coffee_go.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.huyhieu.coffee_go.navigation.route.AppDest
import com.huyhieu.coffee_go.navigation.route.MainDest
import com.huyhieu.coffee_go.screens.Bnb
import com.huyhieu.coffee_go.screens.basket.BasketUi
import com.huyhieu.coffee_go.screens.basket.basket
import com.huyhieu.coffee_go.screens.order_detail.orderDetail

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    navigation<AppDest.Main>(
        //route = AppRoute.Main.route,
        startDestination = MainDest.Bnb,//MainRoute.Home.route,
    ) {
        //Bottom navigation bar
        composable<MainDest.Bnb> {
            Bnb(appNavHostController = navController)
        }
        orderDetail(
            onCloseScreen = navController::popBackStack,
        )
        basket(
            onCloseScreen = navController::popBackStack,
        )
    }
}