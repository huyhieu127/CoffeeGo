package com.huyhieu.domain.repository

import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface CoffeeRepository {
    fun getAllProducts(): Flow<ResponseState<List<Coffee>>>
    fun getLimitProducts(limit: Int): Flow<ResponseState<List<Coffee>>>
    /*fun getProduct(id: String): Flow<ResponseState<Coffee>>
    fun getSortProducts(sort: TypeSort): Flow<ResponseState<List<Coffee>>>
    fun updateProduct(coffeeForm: CoffeeForm): Flow<ResponseState<Coffee>>
    fun addNewProduct(coffeeForm: CoffeeForm): Flow<ResponseState<Coffee>>
    fun deleteProduct(id: String): Flow<ResponseState<Coffee>>*/
}