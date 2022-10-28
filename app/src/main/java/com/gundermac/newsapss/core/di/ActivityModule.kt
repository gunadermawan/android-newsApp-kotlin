package com.gundermac.newsapss.core.di

import com.gundermac.newsapss.ui.bookmark.BookmarkViewModel
import com.gundermac.newsapss.ui.home.HomeViewModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeViewModel(get()) }
}

val bookmarkModule = module {
    factory { BookmarkViewModel(get()) }
}