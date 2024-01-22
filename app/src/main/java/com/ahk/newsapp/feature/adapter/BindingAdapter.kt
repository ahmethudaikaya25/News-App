package com.ahk.newsapp.feature.adapter

import android.webkit.WebView
import android.widget.ImageButton
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.BindingAdapter
import com.ahk.newsapp.R

@BindingAdapter("loadUrl")
fun loadUrl(webView: WebView, url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }
    webView.loadUrl(url)
}

@BindingAdapter("setBookmarkedIcon")
fun setBookmarkedIcon(imageButton: ImageButton, isBookmarked: Boolean) {
    if (isBookmarked) {
        imageButton.setImageDrawable(getDrawable(imageButton.context, R.drawable.selected_bookmark))
    } else {
        imageButton.setImageDrawable(getDrawable(imageButton.context, R.drawable.bookmark))
    }
}
