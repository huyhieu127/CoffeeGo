package com.huyhieu.data.datasource

import com.huyhieu.data.retrofit.service.NewsApiService
import com.huyhieu.data.room.dao.ArticleDao
import com.huyhieu.data.room.entity.ArticleEntity
import com.huyhieu.data.retrofit.di.NewsApi
import com.huyhieu.domain.entity.news.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ArticleDatasourceImpl @Inject constructor(
    @NewsApi
    private val newsApiService: NewsApiService,
    private val articleDao: ArticleDao
) : ArticleDatasource {
    override suspend fun fetchNewsTopHeadlines(country: String): Response<NewsResponse> {
        return newsApiService.getNewsTopHeadlines(country)
    }

    override fun getNewsTopHeadlinesDB(): Flow<List<ArticleEntity>?> {
        return articleDao.getArticles()
    }
}