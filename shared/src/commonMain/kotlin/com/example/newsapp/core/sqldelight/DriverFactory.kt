package com.example.newsapp.core.sqldelight

import app.cash.sqldelight.db.SqlDriver
import com.example.newsapp.configuration.PlatformConfiguration

internal expect class DriverFactory() {

    fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): SqlDriver
}
