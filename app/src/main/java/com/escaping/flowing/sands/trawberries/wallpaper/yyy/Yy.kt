package com.escaping.flowing.sands.trawberries.wallpaper.yyy

import android.app.Application
import android.content.Context
import android.net.Uri
import java.net.URL

class Yy : Application() {
    companion object {
        lateinit var app: Context
        var wallPos = 0
        var picturePos = 0
        lateinit var selectedImageUri: Uri
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}