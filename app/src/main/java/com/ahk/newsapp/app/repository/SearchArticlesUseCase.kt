package com.ahk.newsapp.app.repository

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.ahk.newsapp.base.domain.asCustomException
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class SearchArticlesUseCase(
    private val articleRepository: ArticleRepository,
) {
    operator fun invoke(
        query: String,
        viewModelScope: CoroutineScope,
    ): Flow<PagingData<ArticleEntity>> {
        if (query.length < 3) {
            return flow {
                PagingData.empty<ArticleEntity>()
            }
        }

        return articleRepository.searchNews(query, viewModelScope).map { articleApiPagingData ->
            articleApiPagingData.map { article ->
                article.toEntity()
            }.filter { article ->
                article.title?.let {
                    !it.contains("[Removed]")
                } ?: false
            }
        }.catch {
            Timber.e(it)
            throw it.asCustomException()
        }
    }
}
