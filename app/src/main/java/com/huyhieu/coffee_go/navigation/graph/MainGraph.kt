package com.huyhieu.coffee_go.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.huyhieu.coffee_go.navigation.route.AppDestination
import com.huyhieu.coffee_go.navigation.route.MainDestination
import com.huyhieu.coffee_go.screens.home.homeScreen
import com.huyhieu.coffee_go.screens.order_detail.navigateToOrderDetail
import com.huyhieu.coffee_go.screens.order_detail.orderDetail

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    navigation<AppDestination.Main>(
        //route = AppRoute.Main.route,
        startDestination = MainDestination.Home,//MainRoute.Home.route,
    ) {
        homeScreen(
            onNavigateToViewAllOrderDetail = {},
            onNavigateToOrderDetail = navController::navigateToOrderDetail,
        )
        orderDetail(
            onCloseScreen = navController::popBackStack,
        )
        composable<MainDestination.Profile> {
        }
    }
}