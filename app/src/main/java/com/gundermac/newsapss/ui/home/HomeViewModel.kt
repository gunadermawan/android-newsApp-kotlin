package com.gundermac.newsapss.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gundermac.newsapss.core.data.repository.NewsRepository
import com.gundermac.newsapss.core.data.source.remote.model.CategoryModel
import com.gundermac.newsapss.core.data.source.remote.model.NewsModel
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NewsRepository) : ViewModel() {
    val title = "news"

    val category by lazy {
        MutableLiveData<String>()
    }
    val message by lazy {
        MutableLiveData<String>()
    }
    val news by lazy {
        MutableLiveData<NewsModel>()
    }

    init {
        category.value = ""
        message.value = null
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            try {
                val response = repository.fetchNews(
                    "",
                    "",
                    1
                )
                news.value = response
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
