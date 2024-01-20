package com.ahk.newsapp.feature.home_page.adapter

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.ahk.newsapp.R

@BindingAdapter("buttonStyle")
fun setButtonStyle(button: Button, isSelected: Boolean) {
    if (isSelected) {
        button.setBackgroundColor(button.context.getColor(R.color.md_theme_primary))
        button.setTextColor(button.context.getColor(R.color.md_theme_onPrimary))
    } else {
        button.setBackgroundColor(button.context.getColor(R.color.md_theme_secondary))
        button.setTextColor(button.context.getColor(R.color.md_theme_onSecondary))
    }
}
