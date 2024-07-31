package com.huyhieu.data.datasource.order

import com.huyhieu.data.room.entity.OrderEntity
import kotlinx.coroutines.flow.Flow

interface OrderDatasource {
    suspend fun insertOrder(order: OrderEntity): Long
    suspend fun updateOrder(order: OrderEntity): Int
    suspend fun deleteByOrderIds(vararg orderId: Int): Int
    suspend fun deleteAllOrders(): Int

    fun getOrder(orderId: Int): Flow<OrderEntity?>
    fun getOrdersByIds(vararg orderId: Int): Flow<List<OrderEntity>?>
    fun getAllOrders(): Flow<List<OrderEntity>?>
}