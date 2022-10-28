package com.gundermac.newsapss.core.di

import com.gundermac.newsapss.core.data.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { NewsRepository(get()) }
}