package com.example.newsapp.feature.categories.di

import com.example.newsapp.feature.categories.presentation.newscategory.NewsCategoryViewModel
import org.koin.dsl.module

val newsCategoryModule = module {

    factory<NewsCategoryViewModel> { parameters ->
        NewsCategoryViewModel(
            categoryCodeName = parameters.get(), get(), get()
        )
    }
}