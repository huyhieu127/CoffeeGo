package com.huyhieu.domain.entity.news

data class NewsResponse(
    val status: String?,
    val totalResults: Int,
    val articles: ArrayList<Article>,
)

