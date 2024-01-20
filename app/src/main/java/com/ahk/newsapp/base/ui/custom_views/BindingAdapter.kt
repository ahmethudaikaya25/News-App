package com.ahk.newsapp.base.ui.custom_views

import androidx.databinding.BindingAdapter
import com.ahk.newsapp.base.domain.CustomExceptionData

@BindingAdapter("setErrorContent")
fun setErrorContent(errorItemLayout: ErrorItemLayout, errorContent: CustomExceptionData?) {
    errorContent?.let {
        errorItemLayout.binding.errorModel = it
    }
}
