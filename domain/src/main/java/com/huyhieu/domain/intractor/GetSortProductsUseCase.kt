package com.huyhieu.domain.intractor

import com.huyhieu.domain.common.ResponseState
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.repository.CoffeeRepository
import com.huyhieu.domain.utils.TypeSort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSortProductsUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    operator fun invoke(typeSort: TypeSort): Flow<ResponseState<List<Coffee>>> {
        return coffeeRepository.getSortProducts(typeSort)
    }
}