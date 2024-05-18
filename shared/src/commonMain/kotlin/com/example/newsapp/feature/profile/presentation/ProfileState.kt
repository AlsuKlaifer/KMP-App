package com.example.newsapp.feature.profile.presentation

import com.example.newsapp.feature.auth.data.model.User

data class ProfileState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)