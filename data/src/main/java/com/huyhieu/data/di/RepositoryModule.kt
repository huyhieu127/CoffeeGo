package com.huyhieu.data.di

import com.huyhieu.data.repository.CoffeeRepositoryImpl
import com.huyhieu.data.repository.OrderRepositoryImpl
import com.huyhieu.domain.repository.CoffeeRepository
import com.huyhieu.domain.repository.OrderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCoffeeRepository(coffeeRepositoryImpl: CoffeeRepositoryImpl): CoffeeRepository

    @Binds
    fun bindOrderRepository(orderRepositoryImpl: OrderRepositoryImpl): OrderRepository
}