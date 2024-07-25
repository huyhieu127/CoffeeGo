package com.huyhieu.domain.di

import com.huyhieu.domain.intractor.GetAllProductsUseCase
import com.huyhieu.domain.intractor.GetLimitProductsUseCase
import com.huyhieu.domain.intractor.GetSortProductsUseCase
import com.huyhieu.domain.repository.CoffeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoffeeUseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllProductsUseCase(coffeeRepository: CoffeeRepository) =
        GetAllProductsUseCase(coffeeRepository)

    @Singleton
    @Provides
    fun provideGetLimitProductsUseCase(coffeeRepository: CoffeeRepository) =
        GetLimitProductsUseCase(coffeeRepository)

    @Singleton
    @Provides
    fun provideGetSortProductsUseCase(coffeeRepository: CoffeeRepository) =
        GetSortProductsUseCase(coffeeRepository)
}