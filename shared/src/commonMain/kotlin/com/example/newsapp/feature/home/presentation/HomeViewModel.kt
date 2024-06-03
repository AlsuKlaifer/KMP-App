package com.example.newsapp.feature.home.presentation

import com.example.newsapp.core.firebase.FirebaseCrashlyticsBindings
import com.example.newsapp.core.firebase.ScreenEvent
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.feature.news.data.model.response.Article
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesUseCase
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    firebaseCrashlyticsBindings: FirebaseCrashlyticsBindings?,
) : BaseViewModel<HomeState, HomeAction, HomeEvent>(
    HomeState()
) {
    init {
        loadNews()
        firebaseCrashlyticsBindings?.logScreenEvent(ScreenEvent.HOME)
    }

    override fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnArticleClicked -> onArticleClicked(event.article)
        }
    }

    private fun onArticleClicked(article: Article) {
        scope.launch {
            viewAction = HomeAction.NavigateToDetails(title = article.title)
            viewAction = null
        }
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