package com.ahk.newsapp.app.data.model

import com.ahk.newsapp.feature.home_page.model.ArticleListItemEntity

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
) {
    fun toArticleListItemEntity(): ArticleListItemEntity {
        return ArticleListItemEntity(
            description = description,
            source = source.toSourceEntity(),
            title = title,
            url = url,
            urlToImage = urlToImage,
        )
    }
}
