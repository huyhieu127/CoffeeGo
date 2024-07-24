package com.huyhieu.coffee_go.navigation.route

sealed class MainRoute(val route: String) {
    data object Home : MainRoute("home")
    data object Profile : MainRoute("profile")
}