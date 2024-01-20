package com.ahk.newsapp.app.data.model

import com.ahk.newsapp.feature.home_page.model.SourceEntity

data class Source(
    val id: String,
    val name: String,
) {
    fun toSourceEntity(): SourceEntity {
        return SourceEntity(
            name = name,
        )
    }
}
