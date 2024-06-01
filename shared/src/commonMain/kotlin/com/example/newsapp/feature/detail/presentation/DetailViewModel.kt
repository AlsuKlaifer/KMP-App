package com.example.newsapp.feature.detail.presentation

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.di.PlatformSDK
import com.example.newsapp.feature.news.domain.usecase.GetArticleByTitleUseCase
import kotlinx.coroutines.launch

class DetailViewModel : BaseViewModel<DetailState, DetailAction, DetailEvent>(
    DetailState()
) {

    override fun obtainEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.onBackClicked -> onBackClicked()
        }
    }

    private fun onBackClicked() {
        scope.launch {
            viewAction = DetailAction.NavigateBack
        }
    }

    private val getArticleByTitleUseCase: GetArticleByTitleUseCase by PlatformSDK.lazyInstance()

    //вызвать в аойс с нужным параметром
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