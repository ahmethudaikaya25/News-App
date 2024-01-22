package com.ahk.newsapp.app.repository

import androidx.paging.filter
import androidx.paging.map
import kotlinx.coroutines.flow.map

class FetchBreakingArticlesUseCase(
    private val articleRepository: ArticleRepository,
) {
    operator fun invoke(category: String = "general") =
        articleRepository.getBreakingNews(category = category).map { articleApiPagingData ->
            articleApiPagingData.map { article ->
                article.toEntity()
            }.filter { article ->
                article.title?.let {
                    !it.contains("[Removed]")
                } ?: false
            }
        }
}
