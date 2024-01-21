package com.ahk.newsapp.app.data.model

data class ArticlesResponse(
    val status: String = "",
    val articles: List<ArticleApi> = listOf(),
    val totalResults: Int,
)
