package com.gundermac.newsapss.core.data.repository

import com.gundermac.newsapss.BuildConfig
import com.gundermac.newsapss.core.data.source.remote.model.NewsModel
import com.gundermac.newsapss.core.data.source.remote.network.ApiService

class NewsRepository(private val api: ApiService) {

    suspend fun fetchNews(category: String, query: String, page: Int): NewsModel {
        return api.fetchNews(BuildConfig.API_KEY, "id", category, query, page)
    }

}