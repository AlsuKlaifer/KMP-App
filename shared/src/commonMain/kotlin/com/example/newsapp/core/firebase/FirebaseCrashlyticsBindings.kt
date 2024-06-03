package com.example.newsapp.core.firebase

interface FirebaseCrashlyticsBindings {

    fun logScreenEvent(event: ScreenEvent)
}

enum class ScreenEvent(val details: String) {
    HOME("LAUNCH_HOME"),
    CATEGORIES("LAUNCH_CATEGORIES"),
    PROFILE("LAUNCH_PROFILE"),
    DETAILS("LAUNCH_DETAILS"),
    NEWS_WITH_CATEGORY("LAUNCH_NEWS_WITH_CATEGORY"),
    SIGN_IN("LAUNCH_SIGN_IN"),
    SIGN_UP("LAUNCH_SIGN_UP"),
}