package com.gundermac.newsapss.ui.home

import androidx.lifecycle.ViewModel
import com.gundermac.newsapss.core.data.repository.NewsRepository
import com.gundermac.newsapss.core.data.source.remote.model.CategoryModel

class HomeViewModel(val repository: NewsRepository) : ViewModel() {
    val title = "news"
    val categories= listOf<CategoryModel>(
        CategoryModel("","Berita utama"),
        CategoryModel("business","Bisnis"),
        CategoryModel("entertainment","entertaimen"),
        CategoryModel("general","umum"),
        CategoryModel("health","kesehatan"),
        CategoryModel("science","ilmu sains"),
        CategoryModel("sport","olahraga"),
        CategoryModel("technology","teknologi"),
    )
}
