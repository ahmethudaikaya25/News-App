package com.ahk.newsapp.app.data.model

data class NewsResponse(
    override val status: String = "",
    val articles: List<Article> = listOf(),
    val totalResults: Int,
) : BaseResponse(status)
