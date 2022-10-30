package com.gundermac.newsapss.utils

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.gundermac.newsapss.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class NewsApps : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger()
            androidContext(this@NewsApps)
            modules(networkModule, repositoryModule, viewModelMode, homeModule, bookmarkModule,
                databaseModule)
        }
    }
}