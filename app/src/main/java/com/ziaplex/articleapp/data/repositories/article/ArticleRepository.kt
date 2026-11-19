package com.ziaplex.articleapp.data.repositories.article

import com.ziaplex.articleapp.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ArticleRepository @Inject constructor(
    private val localDataSource: ArticleLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
){
    suspend fun getArticles(): List<Article> = withContext(ioDispatcher) {
        localDataSource.getArticles()
    }

    suspend fun getArticle(id: Long): Article? = withContext(ioDispatcher) {
        localDataSource.getArticle(id)
    }

    suspend fun saveArticle(article: Article): Long = withContext(ioDispatcher) {
        val now = System.currentTimeMillis()
        val toSave = if (article.id == 0L) article.copy(createdAt = now, updatedAt = now)
        else article.copy(updatedAt = now)
        localDataSource.saveArticle(toSave)
    }

    suspend fun deleteArticle(article: Article): Int = withContext(ioDispatcher) {
        localDataSource.deleteArticle(article)
    }
}
