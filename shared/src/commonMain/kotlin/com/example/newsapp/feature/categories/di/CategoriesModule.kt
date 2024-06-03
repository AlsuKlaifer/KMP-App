package com.example.newsapp.com.example.newsapp.feature.categories.di

import com.example.newsapp.feature.categories.presentation.categories.CategoriesViewModel
import org.koin.dsl.module

val categoriesModule = module {

    factory<CategoriesViewModel> { CategoriesViewModel(get()) }

}