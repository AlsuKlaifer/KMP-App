package com.example.newsapp.feature.home.di

import com.example.newsapp.feature.home.presentation.HomeViewModel
import org.koin.dsl.module

val homeModule = module {

    factory<HomeViewModel> { HomeViewModel(get(), get()) }

}