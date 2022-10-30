package com.gundermac.newsapss.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gundermac.newsapss.core.data.source.remote.model.ArticleModel
import com.gundermac.newsapss.core.data.source.remote.model.NewsDao
import com.gundermac.newsapss.utils.SourceConverter

@Database(entities = [ArticleModel::class],
    version = 1,
    exportSchema = false)
@TypeConverters(SourceConverter::class)
abstract class DatabaseClient : RoomDatabase() {
    abstract val newsDao: NewsDao
}