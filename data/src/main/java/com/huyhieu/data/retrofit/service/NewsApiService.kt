package com.huyhieu.data.retrofit.service

import com.huyhieu.domain.entity.news.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getNewsTopHeadlines(
        @Query("country") country: String,//us
        @Query("apiKey") apiKey: String = "b95b426bb7a64620bd754d59deb259a6",//b95b426bb7a64620bd754d59deb259a6
    ): Response<NewsResponse>
}