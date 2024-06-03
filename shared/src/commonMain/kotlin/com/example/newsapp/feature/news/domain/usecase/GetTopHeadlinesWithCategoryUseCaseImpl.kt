package com.example.newsapp.feature.news.domain.usecase

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.model.response.Article
import com.example.newsapp.feature.news.domain.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetTopHeadlinesWithCategoryUseCaseImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val newsRepository: NewsRepository,
) : GetTopHeadlinesWithCategoryUseCase {
    override suspend fun invoke(category: String): ResultWrapper<List<Article>> =
        withContext(ioDispatcher) {
            newsRepository.getTopHeadlinesWithCategory(category)
        }
}