package com.huyhieu.domain.intractor

import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.common.ResponseState
import com.huyhieu.domain.repository.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    operator fun invoke(): Flow<ResponseState<List<Coffee>>> {
        return coffeeRepository.getAllProducts()
    }
}