package com.ziaplex.articleapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ziaplex.articleapp.data.local.entity.ArticleLocalData

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY updatedAt DESC")
    suspend fun getArticles(): List<ArticleLocalData>

    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getArticle(id: Long): ArticleLocalData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleLocalData): Long

    @Delete
    suspend fun deleteArticle(article: ArticleLocalData): Int
}
