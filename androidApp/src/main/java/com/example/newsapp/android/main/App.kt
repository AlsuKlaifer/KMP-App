package com.example.newsapp.android.main

import android.app.Application
import com.example.newsapp.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initShared()
    }
}

fun App.initShared() {
    initKoin()
}