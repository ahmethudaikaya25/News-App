package com.ahk.newsapp.app.data.di

import com.ahk.newsapp.app.data.api.ArticleService
import com.ahk.newsapp.app.data.database.ArticleDao
import com.ahk.newsapp.app.repository.ArticleRepository
import com.ahk.newsapp.app.repository.FetchBookmarkedArticles
import com.ahk.newsapp.app.repository.FetchBookmarkedArticlesUseCase
import com.ahk.newsapp.app.repository.FetchBreakingArticles
import com.ahk.newsapp.app.repository.FetchBreakingArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideFetchBreakingArticles(
        articleService: ArticleService,
    ): FetchBreakingArticles = FetchBreakingArticles(
        articleService = articleService,
    )

    @Provides
    fun provideFetchBookmarkedArticles(
        articleDao: ArticleDao,
    ): FetchBookmarkedArticles = FetchBookmarkedArticles(
        articleDao = articleDao,
    )

    @Provides
    fun getArticleRepository(
        fetchBookmarkedArticles: FetchBookmarkedArticles,
        fetchBreakingArticles: FetchBreakingArticles,
    ): ArticleRepository = ArticleRepository(
        fetchBookmarkedArticles = fetchBookmarkedArticles,
        fetchBreakingArticles = fetchBreakingArticles,
    )

    @Provides
    fun provideGetBookmarkedArticlesUseCase(
        articleRepository: ArticleRepository,
    ): FetchBookmarkedArticlesUseCase = FetchBookmarkedArticlesUseCase(
        articleRepository = articleRepository,
    )

    @Provides
    fun provideGetBreakingNewsUseCase(
        articleRepository: ArticleRepository,
    ): FetchBreakingArticlesUseCase = FetchBreakingArticlesUseCase(
        articleRepository = articleRepository,
    )
}