package com.ahk.newsapp.app.data.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleApi(
    @SerializedName("author") var author: String? = "",
    @SerializedName("content") var content: String? = "",
    @SerializedName("description") var description: String? = "",
    @SerializedName("publishedAt") var publishedAt: String? = "",
    @SerializedName("source") var source: SourceApi = SourceApi(),
    @SerializedName("title") var title: String? = "",
    @PrimaryKey
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage") var urlToImage: String = "",
) : Parcelable {
    fun toEntity(): ArticleEntity {
        return ArticleEntity(
            description = description,
            source = source.toEntity(),
            title = title,
            url = url,
            urlToImage = urlToImage,
            isBookmarked = false,
        )
    }
}
