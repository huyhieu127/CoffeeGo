package com.huyhieu.domain.di

import com.huyhieu.domain.intractor.order.GetAllOrdersUseCase
import com.huyhieu.domain.intractor.order.InsertOrderUseCase
import com.huyhieu.domain.repository.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OrderUseCaseModule {

    @Singleton
    @Provides
    fun provideInsertOrderUseCase(orderRepository: OrderRepository) =
        InsertOrderUseCase(orderRepository)

    @Singleton
    @Provides
    fun provideGetAllOrdersUseCase(orderRepository: OrderRepository) =
        GetAllOrdersUseCase(orderRepository)

}