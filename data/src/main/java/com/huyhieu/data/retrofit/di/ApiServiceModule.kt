package com.huyhieu.data.retrofit.di

import com.google.gson.GsonBuilder
import com.huyhieu.data.BuildConfig
import com.huyhieu.data.retrofit.service.FakeCoffeeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    @FakeCoffeeApi
    fun provideCoffeeApiService(okHttpClient: OkHttpClient): FakeCoffeeApiService {
        val builder = GsonBuilder()
        //builder.registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
        val retrofitBuilder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.FAKE_COFFEE_API)
            .addConverterFactory(GsonConverterFactory.create(builder.create()))
        return retrofitBuilder.build().create(FakeCoffeeApiService::class.java)
    }
}