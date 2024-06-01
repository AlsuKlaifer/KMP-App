package com.example.newsapp.feature.home.presentation

import com.example.newsapp.core.CommonStateFlow
import com.example.newsapp.core.asCommonStateFlow
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.model.response.Article
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesUseCaseImpl
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCaseImpl,
) : ViewModel() {

//    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase by PlatformSDK.lazyInstance()

    private val _viewState = MutableStateFlow(HomeState())
    var state = _viewState.asStateFlow()

    private val _action = MutableSharedFlow<HomeAction?>()
    val action: SharedFlow<HomeAction?> = _action.asSharedFlow()

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

    fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnArticleClicked -> onArticleClicked(event.article)
        }
    }

    private fun onArticleClicked(article: Article) {
        viewModelScope.launch {
            _action.emit(HomeAction.NavigateToDetails(title = article.title))
        }
    }

    private fun loadNews() {
        viewModelScope.launch {
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