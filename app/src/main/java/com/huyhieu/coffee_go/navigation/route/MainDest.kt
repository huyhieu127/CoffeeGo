package com.huyhieu.coffee_go.navigation.route

import com.huyhieu.domain.entity.Order
import com.huyhieu.libs.toData
import kotlinx.serialization.Serializable

sealed class MainDest {
    @Serializable
    object Bnb

    @Serializable
    data class OrderDetail(
        val coffeeId: Int = -1,
        val order: String? = null,
    ) {
        fun getOrder() = order.toData<Order>()
    }

    @Serializable
    object Basket
}