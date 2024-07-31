package com.huyhieu.coffee_go.navigation.route

import kotlinx.serialization.Serializable

sealed class BnbDest {
    @Serializable
    data object Home: BnbDest()

    @Serializable
    data object Shop: BnbDest()

    @Serializable
    data object History: BnbDest()

    @Serializable
    data object Profile: BnbDest()
}