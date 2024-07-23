package com.huyhieu.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "singer")
class SingerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",
    val birthDay: String = "",
)