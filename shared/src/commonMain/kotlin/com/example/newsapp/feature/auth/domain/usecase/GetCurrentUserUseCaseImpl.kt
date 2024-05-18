package com.example.newsapp.feature.auth.domain.usecase

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.auth.data.model.User
import com.example.newsapp.feature.auth.domain.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetCurrentUserUseCaseImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
) : GetCurrentUserUseCase {
    override suspend fun invoke(): ResultWrapper<User> = withContext(ioDispatcher) {
        userRepository.getUser()
    }
}