package com.ahk.newsapp.app.data.api

import com.ahk.newsapp.BuildConfig
import com.ahk.newsapp.app.data.model.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("category") category: String = "general",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): ArticlesResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("language") language: String = "tr",
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): ArticlesResponse
}
