package com.example.newsapp.feature.categories.presentation

import com.example.newsapp.core.CommonStateFlow
import com.example.newsapp.core.asCommonStateFlow
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.di.PlatformSDK
import com.example.newsapp.feature.categories.presentation.model.toUi
import com.example.newsapp.feature.news.domain.usecase.GetCategoriesUseCase
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel() : BaseViewModel<CategoriesState,  CategoriesAction, CategoriesEvent>(
    CategoriesState()
) {

    private val getCategoriesUseCase: GetCategoriesUseCase by PlatformSDK.lazyInstance()

    init {
        loadCategories()
    }

    override fun obtainEvent(event: CategoriesEvent) {
        when (event) {

            else -> {}
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