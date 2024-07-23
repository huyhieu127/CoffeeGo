package com.huyhieu.domain.di

import com.huyhieu.domain.intractor.article.FetchNewsTopHeadlinesUseCase
import com.huyhieu.domain.intractor.article.GetArticleUseCase
import com.huyhieu.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ArticleUseCaseModule {

    @Singleton
    @Provides
    fun provideFetchNewsTopHeadlinesUseCase(articleRepository: ArticleRepository) =
        FetchNewsTopHeadlinesUseCase(articleRepository)

    @Singleton
    @Provides
    fun provideGetArticleUseCase(articleRepository: ArticleRepository) =
        GetArticleUseCase(articleRepository)
}