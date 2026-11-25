package com.ziaplex.articleapp.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ziaplex.articleapp.Article

@Keep
@Entity(tableName = "articles")
data class ArticleLocalData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val category: String,
    val title: String,
    val author: String,
    val date: String,
    val body: String,
    val createdAt: Long,
    val updatedAt: Long
)

fun ArticleLocalData.toModel() = Article(id, category, title, author, date, body, createdAt, updatedAt)
fun Article.toLocalData() = ArticleLocalData(id, category, title, author, date, body, createdAt, updatedAt)