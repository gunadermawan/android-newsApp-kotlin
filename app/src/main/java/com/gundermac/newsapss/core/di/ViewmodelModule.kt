package com.gundermac.newsapss.core.di

import com.gundermac.newsapss.ui.home.HomeViewModel
import org.koin.dsl.module

val viewModelMode = module {
    factory { HomeViewModel(get()) }
}