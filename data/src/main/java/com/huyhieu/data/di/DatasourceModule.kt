package com.huyhieu.data.di

import com.huyhieu.data.datasource.CoffeeDatasource
import com.huyhieu.data.datasource.CoffeeDatasourceImpl
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
    fun bindCoffeeDatasource(coffeeDatasourceImpl: CoffeeDatasourceImpl): CoffeeDatasource
}