package com.huyhieu.data.repository

import com.huyhieu.data.datasource.CoffeeDatasource
import com.huyhieu.data.mapper.ApiMapper
import com.huyhieu.data.retrofit.entity.CoffeeResp
import com.huyhieu.domain.common.ResponseState
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.repository.CoffeeRepository
import com.huyhieu.domain.utils.TypeSort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
    private val coffeeDatasource: CoffeeDatasource
) : CoffeeRepository {
    override fun getAllProducts(): Flow<ResponseState<List<Coffee>>> {
        return ApiMapper.asFlow(toListCoffee()) {
            coffeeDatasource.getAllProducts()
        }
    }

    override fun getLimitProducts(limit: Int): Flow<ResponseState<List<Coffee>>> {
        return  ApiMapper.asFlow(toListCoffee()) {
            coffeeDatasource.getLimitProducts(limit)
        }
    }

    override fun getSortProducts(sort: TypeSort): Flow<ResponseState<List<Coffee>>> {
        return  ApiMapper.asFlow(toListCoffee()) {
            coffeeDatasource.getSortProducts(sort.value)
        }
    }
}

private fun toListCoffee(): List<CoffeeResp>.() -> List<Coffee> = { map { it.toCoffee() } }

private fun CoffeeResp.toCoffee() = Coffee(
    description = description,
    flavorProfile = flavorProfile,
    grindOption = grindOption,
    idStr = idStr,
    id = id,
    imageUrl = imageUrl,
    name = name,
    price = price,
    region = region,
    roastLevel = roastLevel,
    weight = weight
)