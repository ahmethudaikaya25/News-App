package com.ahk.newsapp.base.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import timber.log.Timber

@BindingAdapter("imageUrl")
fun loadUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        try {
            Glide.with(imageView.context).load(url).into(imageView)
        } catch (e: Exception) {
            Timber.e(e)
            return
        }
    } else {
        Timber.e("Url is null")
        return
    }
}
