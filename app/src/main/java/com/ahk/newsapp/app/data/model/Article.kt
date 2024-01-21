package com.ahk.newsapp.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.home_page.model.SourceEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "article")
data class Article(
    @SerializedName("author") var author: String = "",
    @SerializedName("content") var content: String = "",
    @SerializedName("description") var description: String = "",
    @SerializedName("publishedAt") var publishedAt: String = "",
    @SerializedName("source") var source: String = "",
    @SerializedName("title") var title: String = "",
    @PrimaryKey
    @SerializedName("url")
    var url: String = "",
    @SerializedName("urlToImage") var urlToImage: String = "",
) : Parcelable {
    fun toEntity(): ArticleEntity {
        return ArticleEntity(
            description = description,
            source = SourceEntity(name = source),
            title = title,
            url = url,
            urlToImage = urlToImage,
            isBookmarked = true,
        )
    }
}
