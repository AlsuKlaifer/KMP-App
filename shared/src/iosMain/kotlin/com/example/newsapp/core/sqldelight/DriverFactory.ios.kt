package com.example.newsapp.core.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.newsapp.AppDatabase
import com.example.newsapp.configuration.PlatformConfiguration

internal actual class DriverFactory actual constructor() {
    actual fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): SqlDriver {
        return NativeSqliteDriver(
            schema = AppDatabase.Schema,
            name = name,
        )
    }

}