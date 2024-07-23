package com.huyhieu.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song")
class SongEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",
    val singerForm: String = "",//Json SingerEntity
    val authorForm: String = "",//Json AuthorEntity
    val imgUrl: String = "",
    val singerId: Long = 0,
    val authorId: Long = 0,
    val isFavorite: Boolean = false
)