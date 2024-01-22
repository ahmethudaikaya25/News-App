package com.ahk.newsapp.base.ui.custom_views

import androidx.databinding.BindingAdapter
import com.ahk.newsapp.base.domain.CustomExceptionData

@BindingAdapter("setErrorContent")
fun setError(errorItemLayout: ErrorItemLayout, data: CustomExceptionData?) {
    if (data == null) return

    val context = errorItemLayout.context
    errorItemLayout.binding.errorCode.text = data.code.toString()
    errorItemLayout.binding.errorTitle.text = context.getText(data.title)
    errorItemLayout.binding.errorMessage.text = context.getText(data.message)
    errorItemLayout.binding.errorIcon.setImageResource(data.image)
}
