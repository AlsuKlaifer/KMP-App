package com.example.newsapp.feature.detail.presentation

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.feature.news.domain.usecase.GetArticleByTitleUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val title: String,
    private val getArticleByTitleUseCase: GetArticleByTitleUseCase,
) : BaseViewModel<DetailState, DetailAction, DetailEvent>(
    DetailState()
) {

    init {
        loadArticle()
    }

    override fun obtainEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.OnBackClicked -> onBackClicked()
        }
    }

    private fun onBackClicked() {
        scope.launch {
            viewAction = DetailAction.NavigateBack
        }
    }

    private fun loadArticle() {
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