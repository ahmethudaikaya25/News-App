package com.ahk.newsapp.app.data.model

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
)
