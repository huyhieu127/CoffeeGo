package com.huyhieu.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.huyhieu.data.room.CustomConverters
import com.huyhieu.domain.entity.news.Article
import com.huyhieu.domain.entity.news.Source

@Entity(tableName = "article")
class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: Source = Source(),
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String = "",
    val isBookmarked: Boolean = false,
)

fun ArticleEntity.toDomain() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source,
    title = title,
    url = url,
    isBookmarked = isBookmarked,
)