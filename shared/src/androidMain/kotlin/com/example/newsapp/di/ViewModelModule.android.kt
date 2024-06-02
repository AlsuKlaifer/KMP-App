package com.example.newsapp.di

import com.example.newsapp.feature.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun viewModelModule() = module {

    viewModel {
        HomeViewModel(get())
    }

}