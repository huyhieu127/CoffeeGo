package com.huyhieu.data.di

import com.huyhieu.data.datasource.ArticleDatasource
import com.huyhieu.data.repository.ArticleRepositoryImpl
import com.huyhieu.domain.repository.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindArticleRepository(articleRepositoryImpl: ArticleRepositoryImpl): ArticleRepository
}