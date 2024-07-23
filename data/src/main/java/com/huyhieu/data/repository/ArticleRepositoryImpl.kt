package com.huyhieu.data.repository

import com.huyhieu.data.datasource.ArticleDatasource
import com.huyhieu.data.mapper.ApiMapper
import com.huyhieu.data.room.entity.toDomain
import com.huyhieu.domain.entity.news.Article
import com.huyhieu.domain.entity.news.NewsResponse
import com.huyhieu.domain.entity.response.ResponseState
import com.huyhieu.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class ArticleRepositoryImpl @Inject constructor(
    private val articleDatasource: ArticleDatasource
) : ArticleRepository {
    override fun fetchNewsTopHeadlines(country: String): Flow<ResponseState<NewsResponse>> {
        return ApiMapper.asFlow {
            articleDatasource.fetchNewsTopHeadlines(country)
        }
    }

    override fun getNewsTopHeadlinesDB(): Flow<List<Article>?> {
        val news = articleDatasource.getNewsTopHeadlinesDB().map { list ->
            list?.map { it.toDomain() }
        }
        return news
    }
}