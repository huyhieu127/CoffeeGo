package com.huyhieu.domain.intractor.coffee

import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.repository.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    operator fun invoke(id: Int): Flow<ResultState<List<Coffee>>> {
        return coffeeRepository.getProductById(id)
    }
}