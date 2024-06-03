package com.example.newsapp.feature.detail.di

import com.example.newsapp.feature.detail.presentation.DetailViewModel
import org.koin.dsl.module

val detailsModule = module {

    factory<DetailViewModel> { parameters ->
        DetailViewModel(
            title = parameters.get(), get()
        )
    }

}