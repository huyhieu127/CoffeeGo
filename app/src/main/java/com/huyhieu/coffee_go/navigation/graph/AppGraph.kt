package com.huyhieu.coffee_go.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.huyhieu.listentogether.navigation.route.AppRoute

fun NavGraphBuilder.appGraph() {
    composable(AppRoute.Splash.route) {
    }
    composable(AppRoute.Introduce.route) {
    }
    authGraph()
    mainGraph()
    composable(AppRoute.About.route) {
    }
}