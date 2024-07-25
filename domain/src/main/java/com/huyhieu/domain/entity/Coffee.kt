package com.huyhieu.domain.entity

data class Coffee(
    val description: String = "",
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