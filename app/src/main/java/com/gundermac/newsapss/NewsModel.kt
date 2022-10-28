package com.gundermac.newsapss

import java.net.IDN

data class NewsModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>,
)

data class ArticleModel(
    val source:SourceModel?,
    val author:String?,
    val title:String,
    val description:String?,
    val url:String?,
    val urlToImage:String?,
    val publishedAt:String,
    val content:String?,
)

data class SourceModel(
    val id: String?,
    val name:String
)
