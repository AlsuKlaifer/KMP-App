package com.example.newsapp.android.main

import android.app.Application
import com.example.newsapp.configuration.Configuration
import com.example.newsapp.configuration.PlatformConfiguration
import com.example.newsapp.core.utils.deviceType
import com.example.newsapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initShared()
    }
}

fun App.initShared() {

    val configuration = Configuration(
        isDebug = true,
        platformConfiguration = PlatformConfiguration(
            androidContext = this@initShared,
            appVersionName = "1.0",
            appVersionNumber = "1",
            osVersion = "release",
            deviceType = resources.deviceType,
        )
    )

    initKoin(
        configuration
    ) {
        androidContext(this@initShared)

    }
}
