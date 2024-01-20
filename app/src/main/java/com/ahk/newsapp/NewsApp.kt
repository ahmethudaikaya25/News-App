package com.ahk.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApp : Application() {
    // Timber initialization
    override fun onCreate() {
        super.onCreate()
        // Timber initialization
    }
}
