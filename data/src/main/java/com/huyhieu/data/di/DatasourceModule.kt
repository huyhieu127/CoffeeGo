package com.huyhieu.data.di

import com.huyhieu.data.datasource.coffee.CoffeeDatasource
import com.huyhieu.data.datasource.coffee.CoffeeDatasourceImpl
import com.huyhieu.data.datasource.order.OrderDatasource
import com.huyhieu.data.datasource.order.OrderDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DatasourceModule {
    @Binds
    fun bindCoffeeDatasource(coffeeDatasourceImpl: CoffeeDatasourceImpl): CoffeeDatasource

    @Binds
    fun bindOrderDatasource(orderDatasourceImpl: OrderDatasourceImpl): OrderDatasource
}