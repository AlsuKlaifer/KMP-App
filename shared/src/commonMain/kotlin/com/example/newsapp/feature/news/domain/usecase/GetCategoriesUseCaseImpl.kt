package com.example.newsapp.feature.news.domain.usecase

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.model.Category
import com.example.newsapp.feature.news.domain.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetCategoriesUseCaseImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val newsRepository: NewsRepository,
) : GetCategoriesUseCase {
    override suspend fun invoke(title: String): ResultWrapper<List<Category>> =
        withContext(ioDispatcher) {
            newsRepository.getCategories()
        }
}