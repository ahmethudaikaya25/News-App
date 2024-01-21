package com.ahk.newsapp.app.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahk.newsapp.app.data.model.Article
import com.ahk.newsapp.app.data.model.ArticleApi
import kotlinx.coroutines.flow.Flow

class ArticleRepository(
    private val fetchBookmarkedArticles: FetchBookmarkedArticles,
    private val fetchBreakingArticles: FetchBreakingArticles,
) {
    fun getBookmarkedArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { fetchBookmarkedArticles },
        ).flow
    }

    fun getBreakingNews(
        countryCode: String = "tr",
        category: String = "general",
    ): Flow<PagingData<ArticleApi>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                fetchBreakingArticles.apply {
                    this.countryCode = countryCode
                    this.category = category
                }
            },
        ).flow
    }
}
