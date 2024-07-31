package com.huyhieu.coffee_go.navigation.route

import com.huyhieu.domain.entity.Coffee
import com.huyhieu.libs.toData
import kotlinx.serialization.Serializable

sealed class MainDest {
    @Serializable
    object Bnb

    @Serializable
    data class OrderDetail(
        val coffeeId: String = "",
        val coffeeJson: String = "",
        val orderId: Int = -1,
    ) {
        fun getCoffee(): Coffee {
            return checkNotNull(coffeeJson.toData<Coffee>())
        }
    }
}