package com.ahk.newsapp.app.repository

import androidx.paging.map
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class FetchBookmarkedArticlesUseCase(
    private val articleRepository: ArticleRepository,
) {
    operator fun invoke() = articleRepository.getBookmarkedArticles().map {
        it.map { article ->
            article.toEntity()
        }
    }.catch { exception -> throw exception }
}
