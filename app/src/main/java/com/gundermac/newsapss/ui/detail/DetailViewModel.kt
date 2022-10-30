package com.gundermac.newsapss.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gundermac.newsapss.core.data.repository.NewsRepository
import com.gundermac.newsapss.core.data.source.remote.model.ArticleModel
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: NewsRepository) : ViewModel() {
    val isBookmark by lazy {
        MutableLiveData<Int>(0)
    }

    fun find(article: ArticleModel) {
        viewModelScope.launch {
            isBookmark.value = repository.find(article)
        }
    }

    fun bookmark(article: ArticleModel) {
        viewModelScope.launch {
            if (isBookmark.value == 0) repository.save(article)
            else repository.remove(article)
            find(article)
        }
    }
}