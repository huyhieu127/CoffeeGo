package com.huyhieu.data.di

import com.huyhieu.data.datasource.ArticleDatasource
import com.huyhieu.data.datasource.ArticleDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatasourceModule {
    @Singleton
    @Binds
    fun bindArticleDatasource(articleDatasourceImpl: ArticleDatasourceImpl): ArticleDatasource
}