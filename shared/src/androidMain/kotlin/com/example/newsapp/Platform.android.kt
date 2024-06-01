package com.example.newsapp

import com.example.newsapp.feature.home.presentation.HomeViewModel
import io.ktor.client.engine.android.Android
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
actual fun platformModule() = module {

    single {
        Android.create()
    }

    viewModel {
        HomeViewModel(get())
    }
}