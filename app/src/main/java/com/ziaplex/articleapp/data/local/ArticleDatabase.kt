package com.ziaplex.articleapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ziaplex.articleapp.data.local.dao.ArticleDao
import com.ziaplex.articleapp.data.local.entity.ArticleLocalData

@Database(entities = [ArticleLocalData::class], version = 1, exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {
    abstract val articleDao: ArticleDao
}