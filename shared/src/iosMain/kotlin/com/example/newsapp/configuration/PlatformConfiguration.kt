package com.example.newsapp.configuration

actual class PlatformConfiguration(
    actual val appVersionName: String,
    actual val appVersionNumber: String,
    actual val osVersion: String,
    actual val deviceType: Configuration.DeviceType
)