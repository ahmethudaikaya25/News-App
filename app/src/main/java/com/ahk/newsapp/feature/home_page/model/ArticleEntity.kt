package com.ahk.newsapp.feature.home_page.model

import com.ahk.newsapp.base.ui.BaseListItem

data class ArticleEntity(
    val description: String = "",
    val source: SourceEntity = SourceEntity(),
    val title: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val content: String = "",
) : BaseListItem
