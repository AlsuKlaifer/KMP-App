package com.example.newsapp.feature.auth.di

import com.example.newsapp.feature.auth.presentation.signup.SignUpViewModel
import org.koin.dsl.module


val signUpScreenModule = module {

    factory<SignUpViewModel> {
        SignUpViewModel(get(), get())
    }

}