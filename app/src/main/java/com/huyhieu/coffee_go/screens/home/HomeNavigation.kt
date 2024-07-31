package com.huyhieu.coffee_go.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.huyhieu.coffee_go.navigation.route.BnbDest
import com.huyhieu.coffee_go.navigation.route.MainDest
import com.huyhieu.domain.entity.Coffee

fun NavController.navigateToHome() {

}

fun NavGraphBuilder.homeScreen(
    onNavigateToViewAllOrderDetail: () -> Unit,
    onNavigateToOrderDetail: (Coffee) -> Unit,
) {
    composable<BnbDest.Home> {
        HomeScreen(
            onNavigateToViewAllOrderDetail = onNavigateToViewAllOrderDetail,
            onNavigateToOrderDetail = onNavigateToOrderDetail
        )
    }
}
