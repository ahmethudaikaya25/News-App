package com.ahk.newsapp.app.repository.di

import com.ahk.newsapp.app.data.api.ArticleService
import com.ahk.newsapp.app.data.database.ArticleDao
import com.ahk.newsapp.app.repository.ArticleRepository
import com.ahk.newsapp.app.repository.DeleteArticleFromBookmarksUseCase
import com.ahk.newsapp.app.repository.FetchBookmarkedArticles
import com.ahk.newsapp.app.repository.FetchBookmarkedArticlesUseCase
import com.ahk.newsapp.app.repository.FetchBreakingArticles
import com.ahk.newsapp.app.repository.FetchBreakingArticlesUseCase
import com.ahk.newsapp.app.repository.SearchArticles
import com.ahk.newsapp.app.repository.SearchArticlesUseCase
import com.ahk.newsapp.app.repository.ToggleBookmarkUseCase
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
    fun provideSearchArticles(
        articleService: ArticleService,
    ): SearchArticles = SearchArticles(
        articleService = articleService,
    )

    @Provides
    fun getArticleRepository(
        fetchBookmarkedArticles: FetchBookmarkedArticles,
        fetchBreakingArticles: FetchBreakingArticles,
        searchArticles: SearchArticles,
        articleDao: ArticleDao,
    ): ArticleRepository = ArticleRepository(
        fetchBookmarkedArticles = fetchBookmarkedArticles,
        fetchBreakingArticles = fetchBreakingArticles,
        searchArticles = searchArticles,
        articleDao = articleDao,
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

    @Provides
    fun provideSearchArticlesUseCase(
        articleRepository: ArticleRepository,
    ): SearchArticlesUseCase = SearchArticlesUseCase(
        articleRepository = articleRepository,
    )

    @Provides
    fun provideDeleteArticleFromBookmarksUseCase(
        articleRepository: ArticleRepository,
    ): DeleteArticleFromBookmarksUseCase = DeleteArticleFromBookmarksUseCase(
        articleRepository = articleRepository,
    )

    @Provides
    fun provideToggleBookmarkUseCase(
        articleRepository: ArticleRepository,
    ): ToggleBookmarkUseCase = ToggleBookmarkUseCase(
        articleRepository = articleRepository,
    )
}
