package com.ahk.newsapp.app.repository

import androidx.paging.map
import com.ahk.newsapp.R
import com.ahk.newsapp.base.domain.asCustomException
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class FetchBookmarkedArticlesUseCase(
    private val articleRepository: ArticleRepository,
) {
    operator fun invoke() = articleRepository.getBookmarkedArticles().map {
        it.map { article ->
            article.toEntity()
        }
    }.catch { exception ->
        throw exception.asCustomException(
            title = R.string.database_error,
            message = R.string.deleting_article_message,
        )
    }
}
