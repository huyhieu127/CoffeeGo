package com.huyhieu.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.huyhieu.data.room.dao.OrderDao
import com.huyhieu.data.room.entity.OrderEntity


@Database(
    entities = [OrderEntity::class],
    version = 2,
    exportSchema = false,
)
@TypeConverters(CustomConverters::class)
abstract class AppRoomDataBase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
}