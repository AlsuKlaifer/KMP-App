package com.example.newsapp.feature.detail.presentation

import com.example.newsapp.feature.news.data.model.response.Article

data class DetailState(
    val article: Article? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)