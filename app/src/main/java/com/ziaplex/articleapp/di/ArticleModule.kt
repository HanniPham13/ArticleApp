package com.ziaplex.articleapp.di

import com.ziaplex.articleapp.data.local.ArticleDatabase
import com.ziaplex.articleapp.data.repositories.article.ArticleLocalDataSource
import com.ziaplex.articleapp.data.repositories.article.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ArticleModule{
    @Provides
    @ViewModelScoped
    fun provideArticleRepository(localDataSource: ArticleLocalDataSource): ArticleRepository {
        return ArticleRepository(localDataSource)
    }
}