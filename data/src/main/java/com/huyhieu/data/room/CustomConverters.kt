package com.huyhieu.data.room

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.huyhieu.data.room.entity.SingerEntity
import com.huyhieu.domain.entity.news.Source
import com.huyhieu.libs.toData
import com.huyhieu.libs.toJson

class CustomConverters {
    @TypeConverters
    fun fromSinger(singerEntity: SingerEntity): String {
        return singerEntity.toJson()
    }

    @TypeConverters
    fun toSinger(singerJson: String): SingerEntity? {
        return singerJson.toData()
    }

    @TypeConverter
    fun fromSourceArticle(singerEntity: Source): String {
        return singerEntity.toJson()
    }

    @TypeConverter
    fun toSourceArticle(singerJson: String): Source? {
        return singerJson.toData()
    }
}