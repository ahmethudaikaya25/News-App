package com.ahk.newsapp.app.repository

import com.ahk.newsapp.feature.home_page.model.ArticleEntity

class ToggleBookmarkUseCase(
    val articleRepository: ArticleRepository,
) {
    suspend operator fun invoke(article: ArticleEntity) {
        if (article.isBookmarked) {
            articleRepository.deleteArticleFromBookmarks(article.toArticle())
        } else {
            articleRepository.insertArticle(article.toArticle())
        }
    }
}
