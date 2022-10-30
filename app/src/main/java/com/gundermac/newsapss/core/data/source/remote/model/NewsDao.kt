package com.gundermac.newsapss.core.data.source.remote.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {
    @Query("SELECT * FROM tableArticle")
    fun findAll(): LiveData<List<ArticleModel>>

    @Query("SELECT COUNT(*) FROM tableArticle WHERE publishedAt=:publish")
    suspend fun find(publish: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(article: ArticleModel)

    @Delete
    suspend fun remove(article: ArticleModel)

}