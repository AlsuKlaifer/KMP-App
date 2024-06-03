package com.example.newsapp.feature.auth.di

import com.example.newsapp.feature.auth.presentation.signin.SignInViewModel
import org.koin.dsl.module

val signInScreenModule = module {

    factory<SignInViewModel> {
        SignInViewModel(get(), get())
    }

}