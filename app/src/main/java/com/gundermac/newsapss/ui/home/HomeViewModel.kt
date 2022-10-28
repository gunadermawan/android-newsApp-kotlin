package com.gundermac.newsapss.ui.home

import androidx.lifecycle.ViewModel
import com.gundermac.newsapss.core.data.repository.NewsRepository

class HomeViewModel(val repository: NewsRepository) : ViewModel() {
    val title = "news"
}