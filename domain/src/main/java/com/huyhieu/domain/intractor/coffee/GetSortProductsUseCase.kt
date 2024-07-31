package com.huyhieu.domain.intractor.coffee

import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.repository.CoffeeRepository
import com.huyhieu.domain.utils.TypeSort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSortProductsUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    operator fun invoke(typeSort: TypeSort): Flow<ResultState<List<Coffee>>> {
        return coffeeRepository.getSortProducts(typeSort)
    }
}