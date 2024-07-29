package com.huyhieu.coffee_go.navigation.route

sealed class AuthRoute(val route: String) {
    data object LetsStart : AuthRoute("lets_start")
    data object Login : AuthRoute("login")
    data object CreateAccount : AuthRoute("create_account")
    data object FillProfile : AuthRoute("fill_profile")
    data object ChooseNational : AuthRoute("choose_national")
    data object CreateNewPin : AuthRoute("create_new_pin")
    data object SetFingerprint : AuthRoute("set_fingerprint")
    data object ForgotPassword : AuthRoute("forgot_password")
    data object ForgotPasswordOtp : AuthRoute("forgot_password_otp")
    data object CreateNewPassword : AuthRoute("create_new_password")
}