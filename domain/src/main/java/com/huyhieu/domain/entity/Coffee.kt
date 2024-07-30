package com.huyhieu.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Coffee(
    val description: String = "",
    val type: List<OrderOption> = listOf(
        OrderOption(1, "Hot"),
        OrderOption(2, "Iced")
    ),
    val size: List<OrderOption> = listOf(
        OrderOption(1, "Small", "S", 0.0),
        OrderOption(2, "Medium", "M", 0.5),
        OrderOption(3, "Large", "L", 1.0),
    ),
    val flavorProfile: List<OrderOptional> = listOf(),
    val grindOption: List<OrderOptional> = listOf(),
    val idStr: String = "",
    val id: Int = 0,
    val imageUrl: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val region: String = "",
    val roastLevel: Int = 0,
    val weight: Int = 0,
    val isFavorite: Boolean = false,
)

@Serializable
data class OrderOption(
    val id: Int,
    val name: String,
    val shortName: String = "",
    val extraPrice: Double = 0.0,
)

@Serializable
data class OrderOptional(
    val id: Int,
    val name: String,
    val extraPrice: Double = 0.0,
)