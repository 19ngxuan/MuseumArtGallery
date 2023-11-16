package com.example.improvedmuseumartgallery

import android.app.Application
import com.example.improvedmuseumartgallery.data.AppContainer
import com.example.improvedmuseumartgallery.data.DefaultAppContainer

class MuseumApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}