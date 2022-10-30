package com.gundermac.newsapss.core.data.source.remote.model

data class NewsModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>,
)

data class ArticleModel(
    val source: SourceModel?,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
) : java.io.Serializable

data class SourceModel(
    val id: String?,
    val name: String,
) : java.io.Serializable
