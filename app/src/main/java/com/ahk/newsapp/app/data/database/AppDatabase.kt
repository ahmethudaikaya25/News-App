package com.ahk.newsapp.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahk.newsapp.app.data.model.Article

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
