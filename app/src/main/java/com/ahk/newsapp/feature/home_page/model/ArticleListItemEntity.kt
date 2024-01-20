package com.ahk.newsapp.feature.home_page.model

data class ArticleListItemEntity(
    val description: String,
    val source: SourceEntity,
    val title: String,
    val url: String,
    val urlToImage: String,
)
