package com.example.newsapp.core.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.newsapp.AppDatabase
import com.example.newsapp.configuration.PlatformConfiguration

internal actual class DriverFactory actual constructor() {
    actual fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): SqlDriver {
        return AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = platformConfiguration.androidContext,
            name = name,
        )
    }

}