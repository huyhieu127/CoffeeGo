package com.huyhieu.data.room

import androidx.room.TypeConverter
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.libs.toData
import com.huyhieu.libs.toJson

class CustomConverters {
    @TypeConverter
    fun fromCoffee(coffee: Coffee): String {
        return coffee.toJson()
    }

    @TypeConverter
    fun toSinger(coffeeJson: String): Coffee? {
        return coffeeJson.toData()
    }
}