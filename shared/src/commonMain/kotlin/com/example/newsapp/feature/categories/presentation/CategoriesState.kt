package com.example.newsapp.feature.categories.presentation

import com.example.newsapp.feature.categories.presentation.model.CategoryUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class CategoriesState(
    val categories: PersistentList<CategoryUiModel> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed interface CategoriesAction {
}

sealed interface CategoriesEvent {
}
