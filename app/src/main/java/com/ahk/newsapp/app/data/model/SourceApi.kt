package com.ahk.newsapp.app.data.model

import android.os.Parcelable
import com.ahk.newsapp.feature.home_page.model.SourceEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceApi(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = "",
) : Parcelable {
    fun toEntity(): SourceEntity {
        return SourceEntity(
            name = name,
        )
    }

    fun toSource(): String {
        return name
    }
}
