package com.ahk.newsapp.feature.home_page.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceEntity(
    val name: String = "",
) : Parcelable {
    fun toSource(): String {
        return name
    }
}
