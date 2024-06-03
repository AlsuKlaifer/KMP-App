package com.example.newsapp.feature.auth.domain

import com.example.newsapp.core.utils.Result
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.auth.data.model.User

interface UserRepository {

    suspend fun signIn(email: String, password: String): ResultWrapper<User>

    suspend fun signUp(user: User): ResultWrapper<Result>

    suspend fun getCurrentUser(): ResultWrapper<User?>

}