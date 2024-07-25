package com.huyhieu.data.repository

import com.huyhieu.data.datasource.CoffeeDatasource
import com.huyhieu.data.mapper.ApiMapper
import com.huyhieu.data.retrofit.entity.CoffeeResp
import com.huyhieu.domain.common.ResponseState
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.repository.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
    private val coffeeDatasource: CoffeeDatasource
) : CoffeeRepository {
    override fun getAllProducts(): Flow<ResponseState<List<Coffee>>> {
        return ApiMapper.asFlow {
            coffeeDatasource.getAllProducts()
        }.map { state ->
            when (state) {
                is ResponseState.Error -> ResponseState.Error(state.error)
                is ResponseState.Loading -> ResponseState.Loading()
                is ResponseState.Success -> ResponseState.Success(state.data.map { it.toCoffee() })
            }
        }
    }

    override fun getLimitProducts(limit: Int): Flow<ResponseState<List<Coffee>>> {
        return ApiMapper.asFlow {
            coffeeDatasource.getLimitProducts(limit)
        }.map { state ->
            when (state) {
                is ResponseState.Error -> ResponseState.Error(state.error)
                is ResponseState.Loading -> ResponseState.Loading()
                is ResponseState.Success -> ResponseState.Success(state.data.map { it.toCoffee() })
            }
        }
    }
}

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