package com.gundermac.newsapss.core.data.source.remote.network

import com.gundermac.newsapss.core.data.source.remote.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("")
    suspend fun fetchNews(
        @Query("apikey") apikey: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") query: String,
        @Query("page") page: Int,
    ): NewsModel
}