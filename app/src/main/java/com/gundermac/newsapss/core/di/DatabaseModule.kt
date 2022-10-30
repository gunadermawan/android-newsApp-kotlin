package com.gundermac.newsapss.core.di

import android.app.Application
import androidx.room.Room
import com.gundermac.newsapss.core.data.source.local.DatabaseClient
import com.gundermac.newsapss.core.data.source.remote.model.NewsDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideNews(get()) }
}

fun provideDatabase(application: Application): DatabaseClient {
    return Room.databaseBuilder(application, DatabaseClient::class.java, "dbNews.db")
        .fallbackToDestructiveMigration().build()
}

fun provideNews(databaseClient: DatabaseClient): NewsDao {
    return databaseClient.newsDao
}