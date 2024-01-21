package com.ahk.newsapp.app.repository

import androidx.paging.map
import kotlinx.coroutines.flow.map

class FetchBreakingArticlesUseCase(
    private val articleRepository: ArticleRepository,
) {
    operator fun invoke(category: String = "general") =
        articleRepository.getBreakingNews(category = category).map {
            it.map { article ->
                article.toEntity()
            }
        }
}
