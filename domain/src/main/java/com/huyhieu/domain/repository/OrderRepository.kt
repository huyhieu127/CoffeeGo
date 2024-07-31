package com.huyhieu.domain.repository

import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.entity.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun insertOrder(order: Order): Flow<ResultState<Long>>
    fun updateOrder(order: Order): Flow<ResultState<Int>>
    fun deleteByOrderId(vararg orderId: Int): Flow<ResultState<Int>>
    fun deleteAllOrders(): Flow<ResultState<Int>>

    fun getOrder(orderId: Int): Flow<ResultState<Order?>>
    fun getAllOrders(): Flow<ResultState<List<Order>?>>
}