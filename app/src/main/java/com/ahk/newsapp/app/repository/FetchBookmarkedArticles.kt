package com.ahk.newsapp.app.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahk.newsapp.app.data.database.ArticleDao
import com.ahk.newsapp.app.data.model.Article
import com.ahk.newsapp.base.data.runOnDispatcher
import com.ahk.newsapp.base.domain.asCustomException
import kotlinx.coroutines.Dispatchers

class FetchBookmarkedArticles(
    private val articleDao: ArticleDao,
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            runOnDispatcher(Dispatchers.IO) {
                articleDao.getAll(params.loadSize, params.key ?: 0)
            }.let {
                LoadResult.Page(
                    data = it,
                    prevKey = if (params.key == 1) null else (params.key?.minus(1)),
                    nextKey = params.key?.plus(1),
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e.asCustomException())
        }
    }
}
