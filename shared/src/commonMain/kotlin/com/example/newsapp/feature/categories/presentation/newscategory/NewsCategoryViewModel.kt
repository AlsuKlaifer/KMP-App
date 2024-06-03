package com.example.newsapp.feature.categories.presentation.newscategory

import com.example.newsapp.core.firebase.FirebaseCrashlyticsBindings
import com.example.newsapp.core.firebase.ScreenEvent
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.feature.news.data.model.response.Article
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesWithCategoryUseCase
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch


class NewsCategoryViewModel(
    private val categoryCodeName: String,
    private val getTopHeadlinesWithCategoryUseCase: GetTopHeadlinesWithCategoryUseCase,
    firebaseCrashlyticsBindings: FirebaseCrashlyticsBindings?,
) : BaseViewModel<NewsCategoryState, NewsCategoryAction, NewsCategoryEvent>(
    NewsCategoryState(categoryCodeName)
) {
    init {
        loadNews()
        firebaseCrashlyticsBindings?.logScreenEvent(ScreenEvent.NEWS_WITH_CATEGORY)
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