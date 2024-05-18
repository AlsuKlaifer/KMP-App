package com.example.newsapp.feature.auth.di

import com.example.newsapp.core.utils.CoroutineDispatchers
import com.example.newsapp.feature.auth.data.UserRepositoryImpl
import com.example.newsapp.feature.auth.domain.UserRepository
import com.example.newsapp.feature.auth.domain.usecase.GetCurrentUserUseCase
import com.example.newsapp.feature.auth.domain.usecase.GetCurrentUserUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val authModule = DI.Module("authModule") {

    bindProvider<UserRepository> {
        UserRepositoryImpl()
    }

    bindProvider<GetCurrentUserUseCase> {
        GetCurrentUserUseCaseImpl(
            instance(tag = CoroutineDispatchers.IO),
            instance()
        )
    }
}