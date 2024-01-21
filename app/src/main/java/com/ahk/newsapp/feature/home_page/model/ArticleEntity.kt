package com.ahk.newsapp.feature.home_page.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleEntity(
    val description: String? = "",
    val source: SourceEntity? = SourceEntity(),
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    val content: String? = "",
    val isBookmarked: Boolean = false,
) : Parcelable
