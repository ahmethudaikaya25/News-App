package com.ahk.newsapp.app.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahk.newsapp.app.data.model.Article
import com.ahk.newsapp.app.data.model.ArticleApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class ArticleRepository(
    private val fetchBookmarkedArticles: FetchBookmarkedArticles,
    private val fetchBreakingArticles: FetchBreakingArticles,
    private val searchArticles: SearchArticles,
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
        countryCode: String = "us",
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

    fun searchNews(
        searchQuery: String,
        viewModelScope: CoroutineScope,
    ): Flow<PagingData<ArticleApi>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                searchArticles.apply {
                    this.query = searchQuery
                }
            },
        ).flow.cachedIn(viewModelScope)
    }
}
