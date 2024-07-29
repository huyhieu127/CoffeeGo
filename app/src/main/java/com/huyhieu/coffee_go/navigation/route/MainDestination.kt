package com.huyhieu.coffee_go.navigation.route

import com.huyhieu.domain.entity.Coffee
import com.huyhieu.libs.toData
import kotlinx.serialization.Serializable

sealed class MainDestination {
    @Serializable
    object Home

    @Serializable
    data class OrderDetail(
        val coffeeId: String,
        val coffeeJson: String,
    ) {
        fun getCoffee(): Coffee {
            return checkNotNull(coffeeJson.toData<Coffee>())
        }
    }

    @Serializable
    object Profile
}