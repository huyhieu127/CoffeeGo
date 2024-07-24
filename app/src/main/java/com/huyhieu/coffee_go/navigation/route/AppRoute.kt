package com.huyhieu.listentogether.navigation.route

sealed class AppRoute(val route: String) {
    data object Splash : AppRoute("splash")
    data object Introduce : AppRoute("introduce")
    data object Auth : AppRoute("auth")
    data object Main : AppRoute("main")
    data object About : AppRoute("about")
}
