package com.ahk.newsapp.feature.home_page

data class ArticleListItemUIModel(
    val description: String,
    val source: SourceUIModel,
    val title: String,
    val url: String,
    val urlToImage: String,
)
