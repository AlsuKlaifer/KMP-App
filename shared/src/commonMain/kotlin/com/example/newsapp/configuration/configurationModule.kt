package com.example.newsapp.configuration

import com.example.newsapp.core.firebase.FirebaseCrashlyticsBindings
import org.koin.core.module.Module
import org.koin.dsl.module

fun configurationModule(configuration: Configuration): Module = module {

    single<Configuration> { configuration }

    single<PlatformConfiguration> { configuration.platformConfiguration }

    single<FirebaseCrashlyticsBindings> {
        configuration.firebaseCrashlyticsBindings
    }
}

