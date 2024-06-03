package com.example.newsapp.feature.auth.domain.usecase

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.auth.data.model.User

interface SignInUserUseCase {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): ResultWrapper<User>
}
