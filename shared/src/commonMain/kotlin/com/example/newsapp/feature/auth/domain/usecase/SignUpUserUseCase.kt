package com.example.newsapp.feature.auth.domain.usecase

import com.example.newsapp.core.utils.Result
import com.example.newsapp.core.utils.ResultWrapper

interface SignUpUserUseCase {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
    ): ResultWrapper<Result>
}
