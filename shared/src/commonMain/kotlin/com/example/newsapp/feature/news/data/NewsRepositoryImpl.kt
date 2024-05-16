package com.example.newsapp.feature.news.data

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.response.Article
import com.example.newsapp.feature.news.domain.NewsRepository

internal class NewsRepositoryImpl(
    private val newsService: NewsService,
) : NewsRepository {

    override suspend fun getTopHeadlines(): ResultWrapper<List<Article>> = runCatching {
        newsService.getTopHeadlines().articles ?: listOf()
    }.fold(
        onFailure = { ResultWrapper.Failed(it) },
        onSuccess = { ResultWrapper.Success(it) },
    )

}