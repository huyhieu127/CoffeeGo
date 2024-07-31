package com.huyhieu.data.repository

import com.huyhieu.data.datasource.order.OrderDatasource
import com.huyhieu.data.mapper.ResultStateMapperImpl
import com.huyhieu.data.room.entity.OrderEntity
import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.entity.Order
import com.huyhieu.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val datasource: OrderDatasource
) : OrderRepository, ResultStateMapperImpl {
    override fun insertOrder(order: Order): Flow<ResultState<Long>> {
        return flowResultStateDB {
            datasource.insertOrder(order.toEntity())
        }
    }

    override fun updateOrder(order: Order): Flow<ResultState<Int>> {
        return flowResultStateDB {
            datasource.updateOrder(order.toEntity())
        }
    }

    override fun deleteByOrderId(vararg orderId: Int): Flow<ResultState<Int>> {
        return flowResultStateDB {
            datasource.deleteByOrderIds(*orderId)
        }
    }

    override fun deleteAllOrders(): Flow<ResultState<Int>> {
        return flowResultStateDB {
            datasource.deleteAllOrders()
        }
    }

    override fun getOrder(orderId: Int): Flow<ResultState<Order?>> {
        return datasource.getOrder(orderId).asResultStateDB(formatOrder())
    }

    override fun getOrders(): Flow<ResultState<List<Order>?>> {
        return datasource.getOrders().asResultStateDB(formatListOrders())
    }

}

/**
 * Extensions data
 * */
private fun Order.toEntity() = OrderEntity(
    id = id,
    coffeeId = coffeeId,
    quantity = quantity,
    availableInId = availableInId,
    sizeId = sizeId,
)

private fun OrderEntity.toOrder() = Order(
    id = id,
    coffeeId = coffeeId,
    quantity = quantity,
    availableInId = availableInId,
    sizeId = sizeId,
)

private fun formatOrder(): OrderEntity?.() -> Order? = { this?.toOrder() }

private fun formatListOrders(): List<OrderEntity>?.() -> List<Order>? =
    { this?.map { it.toOrder() } }