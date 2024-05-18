package com.example.newsapp.feature.home.presentation

import com.example.newsapp.feature.news.data.model.response.Article
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class HomeState(
    val newsList: PersistentList<Article> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
