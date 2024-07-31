package com.huyhieu.data.datasource.order

import com.huyhieu.data.room.dao.OrderDao
import com.huyhieu.data.room.entity.OrderEntity
import com.huyhieu.domain.common.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderDatasourceImpl @Inject constructor(
    private val orderDao: OrderDao
) : OrderDatasource {
    override suspend fun insertOrder(order: OrderEntity): Long {
        return orderDao.insertOrder(order)
    }

    override suspend fun updateOrder(order: OrderEntity): Int {
        return orderDao.updateOrder(order)
    }

    override suspend fun deleteByOrderIds(vararg orderId: Int): Int {
        return orderDao.deleteByOrderId(*orderId)
    }

    override suspend fun deleteAllOrders(): Int {
        return orderDao.deleteAllOrders()
    }

    override fun getOrder(orderId: Int): Flow<OrderEntity?> {
        return orderDao.getOrder(orderId)
    }

    override fun getOrdersByIds(vararg orderId: Int): Flow<List<OrderEntity>?> {
        return orderDao.getAllOrders()
    }

    override fun getOrders(): Flow<List<OrderEntity>?> {
        return orderDao.getAllOrders()
    }
}