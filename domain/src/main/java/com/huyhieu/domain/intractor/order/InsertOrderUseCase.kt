package com.huyhieu.domain.intractor.order

import com.huyhieu.domain.entity.Order
import com.huyhieu.domain.repository.OrderRepository
import javax.inject.Inject

class InsertOrderUseCase @Inject constructor(private val orderRepository: OrderRepository) {
    operator fun invoke(order: Order) = orderRepository.insertOrder(order)
}