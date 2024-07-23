package com.huyhieu.data.retrofit.di

import com.huyhieu.data.BuildConfig
import com.huyhieu.data.network_config.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideInterceptor() = NetworkInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: NetworkInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.apply {
            addInterceptor(interceptor)
            connectTimeout(160, TimeUnit.SECONDS)
            readTimeout(160, TimeUnit.SECONDS)
            writeTimeout(160, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
        }

        return builder.build()
    }
}