package com.gundermac.newsapss.core.data.repository

import com.gundermac.newsapss.BuildConfig
import com.gundermac.newsapss.core.data.source.remote.model.ArticleModel
import com.gundermac.newsapss.core.data.source.remote.model.NewsDao
import com.gundermac.newsapss.core.data.source.remote.model.NewsModel
import com.gundermac.newsapss.core.data.source.remote.network.ApiService

class NewsRepository(private val api: ApiService, val db: NewsDao) {

    suspend fun fetchNews(category: String, query: String, page: Int): NewsModel {
        return api.fetchNews(BuildConfig.API_KEY, "id", category, query, page)
    }

    suspend fun find(article: ArticleModel) = db.find(article.publishedAt)
    suspend fun save(article: ArticleModel) {
        db.save(article)
    }

    suspend fun remove(article: ArticleModel) {
        db.remove(article)
    }

}