package com.huyhieu.domain.entity

import kotlinx.serialization.Serializable

@Serializable
class Order(
    val id: Int = 0,
    val coffeeId: Int = -1,
    val quantity: Int = -1,
    val availableInId: Int? = null,
    val sizeId: Int? = null,
    val flavorProfileId: Int? = null,
    val grindOptionId: Int? = null,
    val totalPrice: Double = 0.0,
    val note: String = "",
) {
    fun getAvailableIn(coffee: Coffee?) = coffee?.type?.find { it.id == availableInId }
    fun getSize(coffee: Coffee?) = coffee?.size?.find { it.id == sizeId }
    fun getFlavorProfile(coffee: Coffee?) = coffee?.flavorProfile?.find { it.id == flavorProfileId }
    fun getGrindOption(coffee: Coffee?) = coffee?.grindOption?.find { it.id == grindOptionId }
}