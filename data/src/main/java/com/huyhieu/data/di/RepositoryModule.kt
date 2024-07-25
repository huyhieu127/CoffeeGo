package com.huyhieu.data.di

import com.huyhieu.data.repository.CoffeeRepositoryImpl
import com.huyhieu.domain.repository.CoffeeRepository
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
    fun bindCoffeeRepository(coffeeRepositoryImpl: CoffeeRepositoryImpl): CoffeeRepository
}