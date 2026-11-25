package com.ziaplex.articleapp.data.repositories.article

import com.ziaplex.articleapp.Article
import com.ziaplex.articleapp.data.local.ArticleDatabase
import com.ziaplex.articleapp.data.local.dao.ArticleDao
import com.ziaplex.articleapp.data.local.entity.toLocalData
import com.ziaplex.articleapp.data.local.entity.toModel
import javax.inject.Inject
class ArticleLocalDataSource @Inject constructor(private val db: ArticleDatabase) {
    private val articleDao: ArticleDao = db.articleDao

    suspend fun getArticles(): List<Article> = articleDao.getArticles().map { it.toModel() }
    suspend fun getArticle(id: Long): Article? = articleDao.getArticle(id)?.toModel()
    suspend fun saveArticle(article: Article): Long = articleDao.insertArticle(article.toLocalData())
    suspend fun deleteArticle(article: Article): Int = articleDao.deleteArticle(article.toLocalData())
}