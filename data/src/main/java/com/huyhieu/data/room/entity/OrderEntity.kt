package com.huyhieu.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val coffeeId: Int = -1,
    val quantity: Int = -1,
    val availableInId: Int? = null,
    val sizeId: Int? = null,
    val flavorProfileId: Int? = null,
    val grindOptionId: Int? = null,
)