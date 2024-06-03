package com.example.newsapp.core.sqldelight

import com.example.newsapp.AppDatabase
import com.example.newsapp.UsersQueries
import org.koin.dsl.module

val storageModule = module {

    single<AppDatabase> {
        val driver = DriverFactory().createDriver(
            name = "main_db.db",
            platformConfiguration = get(),
        )
        AppDatabase(driver)
    }

    single<UsersQueries> {
        get<AppDatabase>().usersQueries
    }
}