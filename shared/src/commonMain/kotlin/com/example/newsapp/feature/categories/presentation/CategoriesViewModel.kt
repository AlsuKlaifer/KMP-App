package com.example.newsapp.feature.categories.presentation

import com.example.newsapp.core.CommonStateFlow
import com.example.newsapp.core.asCommonStateFlow
import com.example.newsapp.core.vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoriesViewModel(

) : BaseViewModel() {

    private val _viewState = MutableStateFlow(CategoriesState())

    protected var viewState: CategoriesState
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    val viewStates: CommonStateFlow<CategoriesState>
        get() = _viewState.asStateFlow().asCommonStateFlow()

}