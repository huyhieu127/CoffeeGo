package com.huyhieu.coffee_go.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.huyhieu.listentogether.navigation.route.AppRoute
import com.huyhieu.listentogether.navigation.route.AuthRoute

fun NavGraphBuilder.authGraph() {
    navigation(
        startDestination = AuthRoute.LetsStart.route,
        route = AppRoute.Auth.route,
    ) {
        composable(AuthRoute.LetsStart.route) {

        }
        composable(AuthRoute.Login.route) {
        }
        composable(AuthRoute.CreateAccount.route) {
        }
        composable(AuthRoute.FillProfile.route) {
        }
        composable(AuthRoute.ChooseNational.route) {
        }
        composable(AuthRoute.CreateNewPin.route) {
        }
        composable(AuthRoute.SetFingerprint.route) {
        }
        composable(AuthRoute.ForgotPassword.route) {
        }
        composable(AuthRoute.ForgotPasswordOtp.route) {
        }
        composable(AuthRoute.CreateNewPassword.route) {
        }
    }
}