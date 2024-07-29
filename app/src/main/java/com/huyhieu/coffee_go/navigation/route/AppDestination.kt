package com.huyhieu.coffee_go.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed class AppDestination {

    @Serializable
    object Splash

    @Serializable
    object Auth

    @Serializable
    object Main

    @Serializable
    object About

}