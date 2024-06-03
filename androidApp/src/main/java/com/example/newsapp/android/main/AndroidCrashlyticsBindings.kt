package com.example.newsapp.android.main

import com.example.newsapp.core.firebase.FirebaseCrashlyticsBindings
import com.example.newsapp.core.firebase.ScreenEvent
import com.google.firebase.crashlytics.FirebaseCrashlytics

class AndroidCrashlyticsBinding : FirebaseCrashlyticsBindings {

    private val crashlytics by lazy {
        FirebaseCrashlytics.getInstance()
    }

    override fun logScreenEvent(event: ScreenEvent) {
        crashlytics.log(event.details)
    }
}
