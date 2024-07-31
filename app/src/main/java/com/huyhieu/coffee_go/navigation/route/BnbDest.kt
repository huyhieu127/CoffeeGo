package com.huyhieu.coffee_go.navigation.route

import kotlinx.serialization.Serializable

sealed class BnbDest {
    @Serializable
    data object Home: BnbDest()

    @Serializable
    data object Store: BnbDest()

    @Serializable
    data object Order: BnbDest()

    @Serializable
    data object Profile: BnbDest()
}