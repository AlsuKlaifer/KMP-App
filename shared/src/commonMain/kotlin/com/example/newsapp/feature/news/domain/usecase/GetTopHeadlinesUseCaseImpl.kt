package com.example.newsapp.feature.news.domain.usecase

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.response.Article
import com.example.newsapp.feature.news.domain.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetTopHeadlinesUseCaseImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val newsRepository: NewsRepository,
) : GetTopHeadlinesUseCase {
    override suspend fun invoke(): ResultWrapper<List<Article>> = withContext(ioDispatcher) {
        newsRepository.getTopHeadlines()
    }
}