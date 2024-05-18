package com.example.newsapp.feature.news.domain.usecase

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.model.response.Article
import com.example.newsapp.feature.news.domain.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetArticleByTitleUseCaseImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val newsRepository: NewsRepository,
) : GetArticleByTitleUseCase {
    override suspend fun invoke(title: String): ResultWrapper<Article> = withContext(ioDispatcher) {
        newsRepository.getArticleByTitle(title)
    }
}