package com.ahk.newsapp.app.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahk.newsapp.app.data.api.ArticleService
import com.ahk.newsapp.app.data.model.ArticleApi
import com.ahk.newsapp.base.data.runOnDispatcher
import com.ahk.newsapp.base.domain.asCustomException
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class SearchArticles(
    private val articleService: ArticleService,
) : PagingSource<Int, ArticleApi>() {
    var language: String = "tr"
    var query: String = ""
    override fun getRefreshKey(state: PagingState<Int, ArticleApi>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleApi> {
        return try {
            runOnDispatcher(Dispatchers.IO) {
                articleService.searchNews(
                    language = language,
                    pageNumber = params.key ?: 1,
                    pageSize = params.loadSize,
                    searchQuery = query,
                )
            }.let {
                LoadResult.Page(
                    data = it.articles,
                    prevKey = if (params.key == 1) null else (params.key?.minus(1)),
                    nextKey = params.key?.plus(1),
                )
            }
        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Error(e.asCustomException())
        }
    }
}
