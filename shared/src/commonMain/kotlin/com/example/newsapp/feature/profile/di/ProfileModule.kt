package com.example.newsapp.feature.profile.di

import com.example.newsapp.feature.profile.presentation.ProfileViewModel
import org.koin.dsl.module

val profileModule = module {

    factory<ProfileViewModel> { ProfileViewModel(get(), get()) }

}