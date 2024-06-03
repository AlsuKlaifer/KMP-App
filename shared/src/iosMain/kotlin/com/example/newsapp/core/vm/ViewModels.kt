package com.example.newsapp.core.vm

import com.example.newsapp.feature.auth.presentation.signin.SignInViewModel
import com.example.newsapp.feature.auth.presentation.signup.SignUpViewModel
import com.example.newsapp.feature.categories.presentation.CategoriesViewModel
import com.example.newsapp.feature.detail.presentation.DetailViewModel
import com.example.newsapp.feature.home.presentation.HomeViewModel
import com.example.newsapp.feature.profile.presentation.ProfileViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

object ViewModels : KoinComponent {
    fun getHomeViewModel() = get<HomeViewModel>()
    fun getDetailsViewModel(title: String) =
    get<DetailViewModel>(parameters = { parametersOf(title) })

    fun getCategoriesViewModel() = get<CategoriesViewModel>()
    fun getProfileViewModel() = get<ProfileViewModel>()

    fun getSignInViewModel() = get<SignInViewModel>()
    fun getSignUpViewModel() = get<SignUpViewModel>()

}