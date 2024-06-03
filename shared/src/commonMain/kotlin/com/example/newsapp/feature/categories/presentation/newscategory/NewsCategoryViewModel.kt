package com.example.newsapp.feature.categories.presentation.newscategory

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.feature.detail.presentation.DetailAction
import com.example.newsapp.feature.home.presentation.HomeAction
import com.example.newsapp.feature.home.presentation.HomeEvent
import com.example.newsapp.feature.home.presentation.HomeState
import com.example.newsapp.feature.news.data.model.response.Article
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesUseCase
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesWithCategoryUseCase
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch


class NewsCategoryViewModel(
    private val categoryCodeName : String,
    private val getTopHeadlinesWithCategoryUseCase: GetTopHeadlinesWithCategoryUseCase,
) : BaseViewModel<NewsCategoryState, NewsCategoryAction, NewsCategoryEvent>(
    NewsCategoryState(categoryCodeName)
) {
    init {
        loadNews()
    }

    override fun obtainEvent(event: NewsCategoryEvent) {
        when (event) {
            is NewsCategoryEvent.OnArticleClicked -> onArticleClicked(event.article)
            NewsCategoryEvent.OnBackClicked -> onBackClicked()
        }
    }

    private fun onBackClicked() {
        scope.launch {
            viewAction = NewsCategoryAction.NavigateBack
            viewAction = null
        }
    }


    private fun onArticleClicked(article: Article) {
        scope.launch {
            viewAction = NewsCategoryAction.NavigateToDetails(title = article.title)
            viewAction = null
        }
    }

    private fun loadNews() {
        scope.launch {
            viewState = viewState.copy(isLoading = true)

            viewState = when (val response = getTopHeadlinesWithCategoryUseCase(categoryCodeName)) {
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