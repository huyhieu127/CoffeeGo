package com.huyhieu.domain.repository

import com.huyhieu.domain.entity.news.Article
import com.huyhieu.domain.entity.news.NewsResponse
import com.huyhieu.domain.entity.response.ResponseState
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun fetchNewsTopHeadlines(country: String): Flow<ResponseState<NewsResponse>>
    fun getNewsTopHeadlinesDB(): Flow<List<Article>?>
}