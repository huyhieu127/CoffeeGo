package com.huyhieu.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.huyhieu.data.room.dao.ArticleDao
import com.huyhieu.data.room.dao.SingerDao
import com.huyhieu.data.room.dao.SongDao
import com.huyhieu.data.room.entity.ArticleEntity
import com.huyhieu.data.room.entity.SingerEntity
import com.huyhieu.data.room.entity.SongEntity

@Database(
    entities = [
        ArticleEntity::class,
        SongEntity::class,
        SingerEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(CustomConverters::class)
abstract class AppRoomDataBase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun singerDao(): SingerDao
    abstract fun newsDao(): ArticleDao
}