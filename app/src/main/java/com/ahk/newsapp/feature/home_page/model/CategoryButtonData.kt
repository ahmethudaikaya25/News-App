package com.ahk.newsapp.feature.home_page.model

import com.ahk.newsapp.base.ui.BaseListItem

data class CategoryButtonData(
    val name: String = "",
    val isSelected: Boolean = false,
) : BaseListItem
