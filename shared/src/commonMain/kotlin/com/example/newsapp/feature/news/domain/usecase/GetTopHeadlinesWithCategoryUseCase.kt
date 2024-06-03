package com.example.newsapp.feature.news.domain.usecase

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.model.response.Article

interface GetTopHeadlinesWithCategoryUseCase {
    suspend operator fun invoke(category: String): ResultWrapper<List<Article>>
}