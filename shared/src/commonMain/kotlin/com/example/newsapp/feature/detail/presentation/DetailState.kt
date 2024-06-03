package com.example.newsapp.feature.detail.presentation

import com.example.newsapp.feature.news.data.model.response.Article

data class DetailState(
    val article: Article? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed interface DetailAction {
    data object NavigateBack : DetailAction
}

sealed interface DetailEvent {
    data object OnBackClicked : DetailEvent
}
