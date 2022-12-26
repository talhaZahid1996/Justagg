package com.justagg.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }

}