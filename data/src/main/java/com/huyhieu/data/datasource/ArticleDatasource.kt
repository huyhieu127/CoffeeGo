package com.huyhieu.data.datasource

import com.huyhieu.data.room.entity.ArticleEntity
import com.huyhieu.domain.entity.news.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ArticleDatasource {
    suspend fun fetchNewsTopHeadlines(country: String): Response<NewsResponse>

    fun getNewsTopHeadlinesDB(): Flow<List<ArticleEntity>?>
}