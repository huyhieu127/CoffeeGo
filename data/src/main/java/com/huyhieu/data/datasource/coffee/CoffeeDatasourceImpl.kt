package com.huyhieu.data.datasource.coffee

import com.huyhieu.data.retrofit.di.FakeCoffeeApi
import com.huyhieu.data.retrofit.entity.CoffeeResp
import com.huyhieu.data.retrofit.service.FakeCoffeeApiService
import com.huyhieu.domain.entity.CoffeeForm
import retrofit2.Response
import javax.inject.Inject

class CoffeeDatasourceImpl @Inject constructor(
    @FakeCoffeeApi
    private val fakeCoffeeApiService: FakeCoffeeApiService,
) : CoffeeDatasource {
    override suspend fun getAllProducts(): Response<List<CoffeeResp>> {
        return fakeCoffeeApiService.getAllProducts()
    }

    override suspend fun getProduct(id: String): Response<CoffeeResp> {
        return fakeCoffeeApiService.getProduct(id)
    }

    override suspend fun getLimitProducts(limit: Int): Response<List<CoffeeResp>> {
        return fakeCoffeeApiService.getLimitProducts(limit)
    }

    override suspend fun getSortProducts(sort: String): Response<List<CoffeeResp>> {
        return fakeCoffeeApiService.getSortProducts(sort)
    }

    override suspend fun updateProduct(coffeeForm: CoffeeForm): Response<CoffeeResp> {
        return fakeCoffeeApiService.updateProduct(coffeeForm)
    }

    override suspend fun addNewProduct(coffeeForm: CoffeeForm): Response<CoffeeResp> {
        return fakeCoffeeApiService.addNewProduct(coffeeForm)
    }

    override suspend fun deleteProduct(id: String): Response<CoffeeResp> {
        return fakeCoffeeApiService.deleteProduct(id)
    }

}