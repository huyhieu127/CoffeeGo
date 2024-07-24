package com.huyhieu.coffee_go.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.huyhieu.coffee_go.navigation.route.MainRoute
import com.huyhieu.coffee_go.screens.home.HomeScreen
import com.huyhieu.listentogether.navigation.route.AppRoute

fun NavGraphBuilder.mainGraph() {
    navigation(
        startDestination = MainRoute.Home.route,
        route = AppRoute.Main.route,
    ) {
        composable(MainRoute.Home.route) {
            HomeScreen()
        }
        composable(MainRoute.Profile.route) {
        }

    }
}