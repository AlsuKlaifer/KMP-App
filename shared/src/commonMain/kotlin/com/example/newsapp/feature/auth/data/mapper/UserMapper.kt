package com.example.newsapp.feature.auth.data.mapper

import com.example.newsapp.UserDB
import com.example.newsapp.feature.auth.data.model.User

fun UserDB.toDomain(): User = User(
    username = username, avatarUrl = avatarUrl, email = email, password = password
)

fun User.toEntity(): UserDB = UserDB(
    username = username, avatarUrl = avatarUrl, email = email, password = password
)