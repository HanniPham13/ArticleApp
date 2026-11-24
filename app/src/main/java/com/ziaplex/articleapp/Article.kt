package com.ziaplex.articleapp

data class Article(
    val id: Long,
    val category: String,
    val title: String,
    val author: String,
    val date: String,
    val body: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)