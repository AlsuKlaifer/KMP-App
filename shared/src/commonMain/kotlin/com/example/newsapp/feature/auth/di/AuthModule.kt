package com.example.newsapp.feature.auth.di

import com.example.newsapp.core.utils.CoroutineDispatchers
import com.example.newsapp.feature.auth.data.UserRepositoryImpl
import com.example.newsapp.feature.auth.domain.UserRepository
import com.example.newsapp.feature.auth.domain.usecase.GetCurrentUserUseCase
import com.example.newsapp.feature.auth.domain.usecase.GetCurrentUserUseCaseImpl
import com.example.newsapp.feature.auth.domain.usecase.SignInUserUseCase
import com.example.newsapp.feature.auth.domain.usecase.SignInUserUseCaseImpl
import com.example.newsapp.feature.auth.domain.usecase.SignUpUserUseCase
import com.example.newsapp.feature.auth.domain.usecase.SignUpUserUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authModule = module {

    factory<UserRepository> {
        UserRepositoryImpl(get())
    }

    factory<GetCurrentUserUseCase> {
        GetCurrentUserUseCaseImpl(
            get(named(CoroutineDispatchers.IO)),
            get()
        )
    }

    factory<SignUpUserUseCase> {
        SignUpUserUseCaseImpl(
            get(named(CoroutineDispatchers.IO)),
            get()
        )
    }

    factory<SignInUserUseCase> {
        SignInUserUseCaseImpl(
            get(named(CoroutineDispatchers.IO)),
            get()
        )
    }

}