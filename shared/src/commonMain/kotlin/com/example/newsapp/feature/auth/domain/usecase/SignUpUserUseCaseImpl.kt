package com.example.newsapp.feature.auth.domain.usecase

import com.example.newsapp.core.utils.Result
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.auth.data.model.User
import com.example.newsapp.feature.auth.domain.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SignUpUserUseCaseImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
) : SignUpUserUseCase {
    override suspend fun invoke(
        username: String,
        email: String,
        password: String,
    ): ResultWrapper<Result> = withContext(ioDispatcher) {
        userRepository.signUp(
            User(
                username = username, email = email, password = password
            )
        )
    }
}