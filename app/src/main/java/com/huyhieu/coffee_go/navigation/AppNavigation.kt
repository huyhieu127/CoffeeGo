package com.huyhieu.coffee_go.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.huyhieu.coffee_go.navigation.graph.appGraph
import com.huyhieu.listentogether.navigation.route.AppRoute

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppRoute.Main.route) {
        appGraph()
    }
}