package com.huyhieu.domain.repository

import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.utils.TypeSort
import kotlinx.coroutines.flow.Flow

interface CoffeeRepository {
    fun getAllProducts(): Flow<ResultState<List<Coffee>>>
    fun getLimitProducts(limit: Int): Flow<ResultState<List<Coffee>>>
    fun getSortProducts(sort: TypeSort): Flow<ResultState<List<Coffee>>>
    fun getProductById(id: Int): Flow<ResultState<List<Coffee>>>
    /*fun updateProduct(coffeeForm: CoffeeForm): Flow<ResultState<Coffee>>
    fun addNewProduct(coffeeForm: CoffeeForm): Flow<ResultState<Coffee>>
    fun deleteProduct(id: String): Flow<ResultState<Coffee>>*/
}