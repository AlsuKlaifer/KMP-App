package com.example.newsapp.feature.auth.domain

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.auth.data.model.User

interface UserRepository {

    fun getUser(): ResultWrapper<User>

}