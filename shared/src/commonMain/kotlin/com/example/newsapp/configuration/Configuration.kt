package com.example.newsapp.configuration

data class Configuration(
    val platformConfiguration: PlatformConfiguration,
    val isDebug: Boolean,

//    val firebaseCrashlyticsBindings: FirebaseCrashlyticsBindings,
) {

    enum class DeviceType {
        Tablet,
        Phone,
    }
}