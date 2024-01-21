package com.ahk.newsapp.app.data.di

import android.content.Context
import androidx.room.Room
import com.ahk.newsapp.app.data.database.AppDatabase
import com.ahk.newsapp.app.data.database.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database",
        ).build()
    }

    @Provides
    fun provideArticleDao(appDatabase: AppDatabase): ArticleDao {
        return appDatabase.articleDao()
    }
}
