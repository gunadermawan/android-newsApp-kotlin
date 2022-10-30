package com.gundermac.newsapss.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gundermac.newsapss.core.data.repository.NewsRepository
import com.gundermac.newsapss.core.data.source.remote.model.CategoryModel
import com.gundermac.newsapss.core.data.source.remote.model.NewsModel
import kotlinx.coroutines.launch
import kotlin.math.ceil

class HomeViewModel(private val repository: NewsRepository) : ViewModel() {
    val title = "news"

    val category by lazy {
        MutableLiveData<String>()
    }
    val message by lazy {
        MutableLiveData<String>()
    }
    val loading by lazy {
        MutableLiveData<Boolean>()
    }
    val loadingMore by lazy {
        MutableLiveData<Boolean>()
    }
    val news by lazy {
        MutableLiveData<NewsModel>()
    }

    init {
        category.value = ""
        message.value = null
        fetch()
    }

    var query = ""
    var page = 1
    var total = 1

    fun fetch() {
        if (page > 1) loadingMore.value = true else loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.fetchNews(
                    category.value!!,
                    query,
                    page
                )
                news.value = response
                val totalResult: Double = response.totalResults / 20.0
                total = ceil(totalResult).toInt()
                page++
                loading.value = false
                loadingMore.value = false
            } catch (e: Exception) {
                message.value = "something went wrong"
            }
        }
    }

    val categories = listOf(
        CategoryModel("", "Berita utama"),
        CategoryModel("business", "Bisnis"),
        CategoryModel("entertainment", "entertaimen"),
        CategoryModel("general", "umum"),
        CategoryModel("health", "kesehatan"),
        CategoryModel("science", "ilmu sains"),
        CategoryModel("sport", "olahraga"),
        CategoryModel("technology", "teknologi"),
    )
}
