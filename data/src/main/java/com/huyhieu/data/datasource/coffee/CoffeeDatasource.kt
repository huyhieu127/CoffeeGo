package com.huyhieu.data.datasource.coffee

import com.huyhieu.data.retrofit.entity.CoffeeResp
import com.huyhieu.domain.entity.CoffeeForm
import retrofit2.Response

interface CoffeeDatasource {
    suspend fun getAllProducts(): Response<List<CoffeeResp>>
    suspend fun getProduct(id: String): Response<CoffeeResp>
    suspend fun getLimitProducts(limit: Int): Response<List<CoffeeResp>>
    suspend fun getSortProducts(sort: String): Response<List<CoffeeResp>>
    suspend fun updateProduct(coffeeForm: CoffeeForm): Response<CoffeeResp>
    suspend fun addNewProduct(coffeeForm: CoffeeForm): Response<CoffeeResp>
    suspend fun deleteProduct(id: String): Response<CoffeeResp>
}