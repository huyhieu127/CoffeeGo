package com.huyhieu.data.retrofit.service

import com.huyhieu.data.retrofit.entity.CoffeeResp
import com.huyhieu.domain.entity.CoffeeForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface FakeCoffeeApiService {

    @GET("/api")
    suspend fun getAllProducts(): Response<List<CoffeeResp>>

    @GET("/api/{id}")
    suspend fun getProduct(@Path("id") id: Int): Response<List<CoffeeResp>>

    @GET("/api")
    suspend fun getLimitProducts(@Query("limit") limit: Int): Response<List<CoffeeResp>>

    @GET("/api")
    suspend fun getSortProducts(@Query("sort") sort: String): Response<List<CoffeeResp>>

    @PUT("/api")
    suspend fun updateProduct(@Body coffeeForm: CoffeeForm): Response<CoffeeResp>

    @POST("/api")
    suspend fun addNewProduct(@Body coffeeForm: CoffeeForm): Response<CoffeeResp>

    @DELETE("/api/{id}")
    suspend fun deleteProduct(@Path("id") id: String): Response<CoffeeResp>
}