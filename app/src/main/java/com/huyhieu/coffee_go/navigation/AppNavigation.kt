package com.huyhieu.coffee_go.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.huyhieu.coffee_go.navigation.graph.authGraph
import com.huyhieu.coffee_go.navigation.graph.mainGraph
import com.huyhieu.coffee_go.navigation.route.AppDest
import com.huyhieu.listentogether.navigation.route.AppRoute

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppDest.Main) {
        composable(AppRoute.Splash.route) {
        }
        composable(AppRoute.Introduce.route) {
        }
        authGraph(navController)
        mainGraph(navController)
        composable(AppRoute.About.route) {
        }
    }
}