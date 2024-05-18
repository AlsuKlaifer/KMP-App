package com.example.newsapp.feature.auth.data.model

data class User(
    val id: Int,
    val username : String,
    val avatarUrl : String,
    val email: String,
    val password : String
)