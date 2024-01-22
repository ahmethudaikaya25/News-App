package com.ahk.newsapp.app.repository

import androidx.paging.map
import kotlinx.coroutines.flow.map
import timber.log.Timber

class FetchBreakingArticlesUseCase(
    private val articleRepository: ArticleRepository,
) {
    operator fun invoke(category: String = "general") =
        articleRepository.getBreakingNews(category = category).map {
            it.map { article ->
                Timber.d("article: ${article.url} ${article.title} ${article.urlToImage} ${article.description}")
                article.toEntity()
            }
        }
}
