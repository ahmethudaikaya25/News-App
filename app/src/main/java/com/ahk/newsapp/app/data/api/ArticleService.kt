package com.ahk.newsapp.app.data.api

import com.ahk.newsapp.app.data.model.BaseResponse
import retrofit2.http.GET

interface ArticleService {
    @GET("top-headlines")
    fun getTopHeadlines(): BaseResponse
}
