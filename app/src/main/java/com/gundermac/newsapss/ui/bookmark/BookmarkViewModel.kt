package com.gundermac.newsapss.ui.bookmark

import androidx.lifecycle.ViewModel
import com.gundermac.newsapss.core.data.repository.NewsRepository

class BookmarkViewModel(val repository: NewsRepository) : ViewModel() {
    val title = "saved"
}