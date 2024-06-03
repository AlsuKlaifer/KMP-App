package com.example.newsapp.feature.categories.presentation.categories

import com.example.newsapp.core.firebase.FirebaseCrashlyticsBindings
import com.example.newsapp.core.firebase.ScreenEvent
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.feature.categories.presentation.model.toUi
import com.example.newsapp.feature.news.domain.usecase.GetCategoriesUseCase
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    firebaseCrashlyticsBindings: FirebaseCrashlyticsBindings?,
) : BaseViewModel<CategoriesState, CategoriesAction, CategoriesEvent>(
    CategoriesState()
) {

    init {
        loadCategories()
        firebaseCrashlyticsBindings?.logScreenEvent(ScreenEvent.CATEGORIES)
    }

    override fun obtainEvent(event: CategoriesEvent) {
        when (event) {
            is CategoriesEvent.OnCategoryClick -> onCategoryClicked(event.codeName)
        }
    }

    private fun onCategoryClicked(codeName: String) {
        scope.launch {
            viewAction = CategoriesAction.NavigateToCategoryNews(codeName = codeName)
            viewAction = null
        }
    }


    private fun loadCategories() {
        scope.launch {
            viewState = viewState.copy(isLoading = true)

            viewState = when (val response = getCategoriesUseCase()) {
                is ResultWrapper.Failed -> {
                    viewState.copy(isLoading = false, error = response.errorMessage)
                }

                is ResultWrapper.Success -> {
                    val categories = response.data
                        .map { it.toUi() }
                        .toPersistentList()
                    viewState.copy(categories = categories)
                }
            }
        }
    }

}