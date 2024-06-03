package com.example.newsapp.feature.auth.data

import com.example.newsapp.UsersQueries
import com.example.newsapp.core.utils.Result
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.auth.data.mapper.toDomain
import com.example.newsapp.feature.auth.data.mapper.toEntity
import com.example.newsapp.feature.auth.data.model.User
import com.example.newsapp.feature.auth.domain.UserRepository

class UserRepositoryImpl(
    private val usersQueries: UsersQueries,
) : UserRepository {

    override suspend fun signIn(email: String, password: String): ResultWrapper<User> =
        runCatching {
            usersQueries.getUser(email, password).executeAsOneOrNull()?.toDomain()
        }.fold(
            onSuccess = {
                if (it == null) {
                    ResultWrapper.Failed(
                        IllegalArgumentException("User doesn't exist"),
                        "User doesn't exist"
                    )
                } else {
                    cachedCurrentUser = it
                    ResultWrapper.Success(it)
                }
            },
            onFailure = {
                ResultWrapper.Failed(it)
            }
        )


    override suspend fun signUp(user: User): ResultWrapper<Result> =
        runCatching {
            usersQueries.insertUser(user.toEntity())
        }.fold(
            onSuccess = {
                ResultWrapper.Success(Result.SUCCESS)
            },
            onFailure = {
                ResultWrapper.Failed(it)
            }
        )

    override suspend fun getCurrentUser(): ResultWrapper<User?> =
        ResultWrapper.Success(cachedCurrentUser)


    companion object {
        var cachedCurrentUser: User? = null
    }
}
