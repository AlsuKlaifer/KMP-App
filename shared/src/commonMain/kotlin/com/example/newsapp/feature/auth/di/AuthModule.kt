package com.example.newsapp.feature.auth.di

import com.example.newsapp.core.utils.CoroutineDispatchers
import com.example.newsapp.feature.auth.data.UserRepositoryImpl
import com.example.newsapp.feature.auth.domain.UserRepository
import com.example.newsapp.feature.auth.domain.usecase.GetCurrentUserUseCase
import com.example.newsapp.feature.auth.domain.usecase.GetCurrentUserUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authModule = module {

    factory<UserRepository> {
        UserRepositoryImpl()
    }

    factory<GetCurrentUserUseCase> {
        GetCurrentUserUseCaseImpl(
            get(named(CoroutineDispatchers.IO)),
            get()
        )
    }
}