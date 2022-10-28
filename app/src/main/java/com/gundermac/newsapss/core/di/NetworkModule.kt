package com.gundermac.newsapss.core.di

import com.gundermac.newsapss.core.data.source.remote.network.provideNewsApi
import com.gundermac.newsapss.core.data.source.remote.network.provideOkHttpClient
import com.gundermac.newsapss.core.data.source.remote.network.provideRetrofit
import org.koin.dsl.module

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideNewsApi(get()) }
}