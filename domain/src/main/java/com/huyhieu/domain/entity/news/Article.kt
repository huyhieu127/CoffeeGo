package com.huyhieu.domain.entity.news

data class Article(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: Source = Source(),
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String = "",
    var isBookmarked: Boolean = false
)