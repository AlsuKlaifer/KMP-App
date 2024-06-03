package com.example.newsapp.configuration

import com.example.newsapp.core.firebase.FirebaseCrashlyticsBindings

data class Configuration(
    val platformConfiguration: PlatformConfiguration,
    val isDebug: Boolean,

    val firebaseCrashlyticsBindings: FirebaseCrashlyticsBindings,
) {

    enum class DeviceType {
        Tablet,
        Phone,
    }
}