package com.ahk.newsapp.base.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ahk.newsapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

@BindingAdapter("imageUrl")
fun loadUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        try {
            Glide.with(imageView.context).load(url).apply(
                RequestOptions().placeholder(R.drawable.default_error_icon)
                    .error(R.drawable.default_error_icon),
            ).into(imageView)
        } catch (e: Exception) {
            Timber.e(e)
            return
        }
    } else {
        Timber.e("Url is null")
        return
    }
}
