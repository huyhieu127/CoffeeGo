package com.huyhieu.data.retrofit.di

import com.google.gson.GsonBuilder
import com.huyhieu.data.retrofit.service.NewsApiService
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
    @NewsApi
    fun provideNewsApiService(okHttpClient: OkHttpClient): NewsApiService {
        val builder = GsonBuilder()
        //builder.registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
        val retrofitBuilder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(builder.create()))
        return retrofitBuilder.build().create(NewsApiService::class.java)
    }
}