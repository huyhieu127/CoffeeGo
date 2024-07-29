package com.huyhieu.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Coffee(
    val description: String = "",
    val type: List<String> = listOf(
        "Hot", "Iced"
    ),
    val size: List<Size> = listOf(
        Size(1, "Small", "S", 0.0),
        Size(2, "Medium", "M", 0.5),
        Size(3, "Large", "L", 1.0),
    ),
    val flavorProfile: List<String> = listOf(),
    val grindOption: List<String> = listOf(),
    val idStr: String = "",
    val id: Int = 0,
    val imageUrl: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val region: String = "",
    val roastLevel: Int = 0,
    val weight: Int = 0
)

@Serializable
data class Size(
    val id: Int,
    val name: String,
    val shortName: String,
    val extraPrice: Double,
)