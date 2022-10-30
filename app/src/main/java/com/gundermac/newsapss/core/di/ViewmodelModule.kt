package com.gundermac.newsapss.core.di

import com.gundermac.newsapss.ui.detail.DetailViewModel
import com.gundermac.newsapss.ui.home.HomeViewModel
import org.koin.dsl.module

val HomeViewModelModule = module {
    factory { HomeViewModel(get()) }
}

val DetailViewModelModule = module {
    factory { DetailViewModel(get()) }
}