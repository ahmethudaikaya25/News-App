package com.ahk.newsapp.app.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahk.newsapp.app.data.api.ArticleService
import com.ahk.newsapp.app.data.model.ArticleApi
import com.ahk.newsapp.base.data.runOnDispatcher
import com.ahk.newsapp.base.domain.asCustomException
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class FetchBreakingArticles(
    private val articleService: ArticleService,
) : PagingSource<Int, ArticleApi>() {
    var countryCode: String = "tr"
    var category: String = "general"

    override fun getRefreshKey(state: PagingState<Int, ArticleApi>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleApi> {
        return try {
            runOnDispatcher(Dispatchers.IO) {
                articleService.getBreakingNews(
                    pageNumber = params.key ?: 1,
                    pageSize = params.loadSize,
                    countryCode = countryCode,
                    category = category,
                )
            }.let {
                Timber.d("load: ${it.articles}")
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
