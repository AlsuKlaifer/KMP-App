package com.example.newsapp.com.example.newsapp.feature.detail.di

import com.example.newsapp.feature.detail.presentation.DetailViewModel
import org.koin.dsl.module

val detailsModule = module {

    factory<DetailViewModel> { DetailViewModel(get()) }

}