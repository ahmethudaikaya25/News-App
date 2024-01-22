package com.ahk.newsapp.app.repository

import com.ahk.newsapp.app.data.model.Article

class DeleteArticleFromBookmarksUseCase(
    val articleRepository: ArticleRepository,
) {
    suspend operator fun invoke(article: Article) {
        articleRepository.deleteArticleFromBookmarks(article)
    }
}
