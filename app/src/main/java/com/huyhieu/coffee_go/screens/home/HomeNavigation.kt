package com.huyhieu.coffee_go.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.huyhieu.coffee_go.navigation.route.MainDestination
import com.huyhieu.domain.entity.Coffee

fun NavController.navigateToHome() {

}

fun NavGraphBuilder.homeScreen(
    onNavigateToViewAllOrderDetail: () -> Unit,
    onNavigateToOrderDetail: (Coffee) -> Unit,
) {
    composable<MainDestination.Home> {
        HomeScreen(
            onNavigateToViewAllOrderDetail = onNavigateToViewAllOrderDetail,
            onNavigateToOrderDetail = onNavigateToOrderDetail
        )
    }
}
