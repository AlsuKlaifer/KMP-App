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

class CategoriesViewModel() : BaseViewModel() {

    private val getCategoriesUseCase: GetCategoriesUseCase by PlatformSDK.lazyInstance()

    private val _viewState = MutableStateFlow(CategoriesState())

    protected var viewState: CategoriesState
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    val viewStates: CommonStateFlow<CategoriesState>
        get() = _viewState.asStateFlow().asCommonStateFlow()

    init {
        loadCategories()
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