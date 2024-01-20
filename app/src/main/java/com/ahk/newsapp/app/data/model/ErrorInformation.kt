package com.ahk.newsapp.app.data.model

data class ErrorInformation(
    override val status: String = "",
    val code: String = "",
    val message: String = "",
) : BaseResponse(status)
