package com.example.newsapp

import com.example.newsapp.feature.home.presentation.HomeViewModel
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        Darwin.create()
    }
    single {
        HomeViewModel(get())
    }
}

object ViewModels : KoinComponent {
    fun getHomeViewModel() = get<HomeViewModel>()
}
