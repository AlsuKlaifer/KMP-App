package com.example.newsapp.feature.detail.presentation

import com.example.newsapp.core.CommonStateFlow
import com.example.newsapp.core.asCommonStateFlow
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.di.PlatformSDK
import com.example.newsapp.feature.news.domain.usecase.GetArticleByTitleUseCase
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesUseCase
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : BaseViewModel() {

    private val getArticleByTitleUseCase: GetArticleByTitleUseCase by PlatformSDK.lazyInstance()

    private val _viewState = MutableStateFlow(DetailState())

    protected var viewState: DetailState
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    val viewStates: CommonStateFlow<DetailState>
        get() = _viewState.asStateFlow().asCommonStateFlow()

    fun loadArticle(title: String) {
        scope.launch {
            viewState = viewState.copy(isLoading = true)

            viewState = when (val response = getArticleByTitleUseCase(title)) {
                is ResultWrapper.Failed -> {
                    viewState.copy(isLoading = false, error = response.errorMessage)
                }

                is ResultWrapper.Success -> {
                    viewState.copy(article = response.data)
                }
            }
        }
    }

}