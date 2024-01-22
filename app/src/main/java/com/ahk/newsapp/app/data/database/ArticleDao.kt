package com.ahk.newsapp.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahk.newsapp.app.data.model.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article LIMIT :limit OFFSET :offset")
    fun getAll(limit: Int, offset: Int): List<Article>

    @Query("SELECT * FROM article WHERE url = :url")
    fun getArticle(url: String): Article?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Query("DELETE FROM article WHERE url = :url")
    fun deleteArticle(url: String): Int
}
