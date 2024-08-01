package com.huyhieu.domain.intractor.order

import com.huyhieu.domain.repository.OrderRepository
import javax.inject.Inject

class DeleteOrderByIdsUseCase @Inject constructor(private val orderRepository: OrderRepository) {
    operator fun invoke(vararg orderId: Int) =
        orderRepository.deleteByOrderId(*orderId)
}