package com.example.newsapp.feature.home.presentation

import com.example.newsapp.core.CommonStateFlow
import com.example.newsapp.core.asCommonStateFlow
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.di.PlatformSDK
import com.example.newsapp.feature.news.data.response.Article
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesUseCase
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeState(
    val newsList: PersistentList<Article> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

class HomeViewModel() : BaseViewModel() {

    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase by PlatformSDK.lazyInstance()

    private val _viewState = MutableStateFlow(HomeState())

    protected var viewState: HomeState
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    val viewStates: CommonStateFlow<HomeState>
        get() = _viewState.asStateFlow().asCommonStateFlow()

    init {
        loadNews()
    }

    private fun loadNews() {
        scope.launch {
            viewState = viewState.copy(isLoading = true)

            viewState = when (val response = getTopHeadlinesUseCase()) {
                is ResultWrapper.Failed -> {
                    viewState.copy(isLoading = false, error = response.errorMessage)
                }

                is ResultWrapper.Success -> {
                    viewState.copy(newsList = response.data.toPersistentList())
                }
            }
        }
    }


}