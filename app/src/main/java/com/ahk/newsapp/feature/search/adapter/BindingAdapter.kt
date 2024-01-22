package com.ahk.newsapp.feature.search.adapter

import android.text.Editable
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter

@BindingAdapter("onSearchTextChange")
fun setOnTextChangedListener(editText: EditText, editable: (Editable?) -> Unit) {
    editText.addTextChangedListener {
        editable(it)
    }
}
