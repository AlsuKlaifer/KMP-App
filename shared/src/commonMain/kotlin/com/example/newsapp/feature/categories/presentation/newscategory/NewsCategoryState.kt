package com.example.newsapp.feature.categories.presentation.newscategory

import com.example.newsapp.feature.categories.presentation.model.CategoryUiModel
import com.example.newsapp.feature.detail.presentation.DetailEvent
import com.example.newsapp.feature.news.data.model.response.Article
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf


data class NewsCategoryState(
    val category : String,
    val newsList: PersistentList<Article> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed interface NewsCategoryAction {
    data class NavigateToDetails(var title: String? = null) : NewsCategoryAction
    data object NavigateBack: NewsCategoryAction
}

sealed interface NewsCategoryEvent {
    data class OnArticleClicked(val article: Article) : NewsCategoryEvent
    data object OnBackClicked : NewsCategoryEvent
}
