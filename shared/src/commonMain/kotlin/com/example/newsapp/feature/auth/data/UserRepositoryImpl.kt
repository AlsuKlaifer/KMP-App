package com.example.newsapp.feature.auth.data

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.auth.data.model.User
import com.example.newsapp.feature.auth.domain.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class UserRepositoryImpl : UserRepository {

    override fun getUser(): ResultWrapper<User> = ResultWrapper.Success(user)

    companion object {
        val user = User(
            id = 1,
            username = "Johnny Sins",
            email = "johnny.sins@example.com",
            password = "qwerty1234",
            avatarUrl = "https://sun9-10.userapi.com/impg/AivGF1DGgRXO_UHvU0ik7faJv3Og-kn0Jd_--g/mrdWCDgnlNA.jpg?size=400x400&quality=95&sign=d5c4e18c37c800a486366aec240f7054&type=album"
        )
    }

    override suspend fun getUser(user: User): User? = withContext(Dispatchers.IO) {
        User(
            id = 1,
            username = "Johnny Sins",
            email = "johnny.sins@example.com",
            password = "qwerty1234",
            avatarUrl = "https://sun9-10.userapi.com/impg/AivGF1DGgRXO_UHvU0ik7faJv3Og-kn0Jd_--g/mrdWCDgnlNA.jpg?size=400x400&quality=95&sign=d5c4e18c37c800a486366aec240f7054&type=album"
        )
    }
}