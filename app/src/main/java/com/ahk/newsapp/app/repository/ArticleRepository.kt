package com.ahk.newsapp.app.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.ahk.newsapp.R
import com.ahk.newsapp.app.data.database.ArticleDao
import com.ahk.newsapp.app.data.model.Article
import com.ahk.newsapp.app.data.model.ArticleApi
import com.ahk.newsapp.base.data.runOnDispatcher
import com.ahk.newsapp.base.domain.CustomException
import com.ahk.newsapp.base.domain.CustomExceptionData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticleRepository(
    private val fetchBookmarkedArticles: FetchBookmarkedArticles,
    private val fetchBreakingArticles: FetchBreakingArticles,
    private val searchArticles: SearchArticles,
    private val articleDao: ArticleDao,
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

    suspend fun deleteArticleFromBookmarks(article: Article) {
        runOnDispatcher(Dispatchers.IO) {
            try {
                articleDao.deleteArticle(article.url)
            } catch (exception: Exception) {
                throw CustomException.DatabaseException(
                    CustomExceptionData(
                        title = R.string.database_error,
                        message = R.string.deleting_article_message,
                    ),
                )
            }
        }
    }

    suspend fun insertArticle(article: Article) {
        runOnDispatcher(Dispatchers.IO) {
            try {
                articleDao.insert(article)
            } catch (exception: Exception) {
                throw CustomException.DatabaseException(
                    CustomExceptionData(
                        title = R.string.database_error,
                        message = R.string.inserting_article_message,
                    ),
                )
            }
        }
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
        ).flow.map { pagingData ->
            pagingData.map {
                it.copy(
                    isBookmarked = isArticleBookmarked(it.toArticle()),
                )
            }
        }
    }

    private suspend fun isArticleBookmarked(article: Article): Boolean {
        return runOnDispatcher(Dispatchers.IO) {
            try {
                articleDao.getArticle(article.url) != null
            } catch (exception: Exception) {
                throw CustomException.DatabaseException(
                    CustomExceptionData(
                        title = R.string.database_error,
                        message = R.string.error_checking_article_message,
                    ),
                )
            }
        }
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
        ).flow.cachedIn(viewModelScope).map { pagingData ->
            pagingData.map {
                it.copy(
                    isBookmarked = isArticleBookmarked(it.toArticle()),
                )
            }
        }
    }
}
