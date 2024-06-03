package com.example.newsapp.feature.news.data

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.model.Category
import com.example.newsapp.feature.news.data.model.response.Article
import com.example.newsapp.feature.news.domain.NewsRepository

internal class NewsRepositoryImpl(
    private val newsService: NewsService,
) : NewsRepository {

    override suspend fun getTopHeadlines(): ResultWrapper<List<Article>> = runCatching {
        newsService.getTopHeadlines().articles.filter { !it.urlToImage.isNullOrBlank() }
    }.fold(
        onFailure = { ResultWrapper.Failed(it) },
        onSuccess = { ResultWrapper.Success(it) },
    )

    override suspend fun getTopHeadlinesWithCategory(category: String): ResultWrapper<List<Article>> =
        runCatching {
            newsService.getTopHeadlinesWithCategory(category).articles.filter {
                !it.urlToImage.isNullOrBlank()
            }
        }.fold(
            onFailure = { ResultWrapper.Failed(it) },
            onSuccess = { ResultWrapper.Success(it) },
        )

    override suspend fun getArticleByTitle(title: String): ResultWrapper<Article> = runCatching {
        val response = newsService.getArticleByTitle(title)
        response.articles.first()
    }.fold(
        onFailure = { ResultWrapper.Failed(it) },
        onSuccess = { ResultWrapper.Success(it) },
    )

    override suspend fun getCategories(): ResultWrapper<List<Category>> = runCatching {
        categories
    }.fold(
        onFailure = { ResultWrapper.Failed(it) },
        onSuccess = { ResultWrapper.Success(it) },
    )

    companion object {
        private val categories = listOf(
            Category("Business", codeName = "business"),
            Category("Entertainment", codeName = "entertainment"),
            Category("General", codeName = "general"),
            Category("Health", codeName = "health"),
            Category("Science", codeName = "science"),
            Category("Sports", codeName = "sports"),
            Category("Technology", codeName = "technology"),
        )
    }
}